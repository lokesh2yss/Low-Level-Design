package chat_application.service;

import chat_application.entities.*;
import parking_lot.entities.ParkingTicket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ChatService {
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Chat> chats = new ConcurrentHashMap<>();

    public User createUser(String name) {
        User user = new User(name);
        users.put(user.getId(), user);
        return user;
    }
    public Chat createOneToOneChat(String userId1, String userId2) {
        User user1 = users.get(userId1);
        User user2 = users.get(userId2);
        OneToOneChat chat = new OneToOneChat(user1, user2);
        chats.put(chat.getId(), chat);
        return chat;
    }
    public Chat createGroupChat(String groupName, List<String> memberIds) {
        List<User> members = new ArrayList<>();
        members = memberIds.stream()
                .map(users::get)
                .collect(Collectors.toList());
        Chat chat = new GroupChat(groupName, members);
        chats.put(chat.getId(), chat);
        return chat;
    }
    public void sendMessage(String senderId, String chatId, String content) {
        User sender = users.get(senderId);
        Chat chat = chats.get(chatId);
        if (chat == null) {
            System.err.println("Error: Chat not found with ID: " + chatId);
            return;
        }

        if (!chat.getMembers().contains(sender)) {
            System.err.println("Error: Sender " + sender.getName() + " is not a member of this chat.");
            return;
        }
        Message message = new Message(sender, content);
        chat.addMessage(message);
        // Notify all members of the chat (Observer pattern)
        for(User member: chat.getMembers()) {
            if(!member.equals(sender)) { // Do not send a notification to the sender
                member.onMessageReceived(message, chat);
            }
        }
    }
    public List<Message> printChatHistory(String chatId) {
        Chat chat = chats.get(chatId);
        if(chat != null) {
            return chat.getMessages();
        }
        return new ArrayList<>();
    }
    public List<Chat> getUserChats(String userId) {
        return chats.values().stream()
                .filter(chat -> chat.getMembers().contains(users.get(userId)))
                .collect(Collectors.toList());
    }
}

