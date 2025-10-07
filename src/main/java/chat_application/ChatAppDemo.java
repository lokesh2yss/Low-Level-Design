package chat_application;

import chat_application.entities.Chat;
import chat_application.entities.Message;
import chat_application.entities.User;
import chat_application.service.ChatService;

import java.util.List;

public class ChatAppDemo {
    public static void main(String[] args) {
        // 1. Initialize the Mediator (ChatService)
        ChatService chatService = new ChatService();

        // 2. Create and register users
        User alice = chatService.createUser("Alice");
        User bob = chatService.createUser("Bob");
        User charlie = chatService.createUser("Charlie");

        System.out.println("--- Users registered in the system ---");
        System.out.println();

        // 3. Scenario 1: One-on-one chat between Alice and Bob
        System.out.println("--- Starting one-on-one chat between Alice and Bob ---");
        Chat aliceBobChat = chatService.createOneToOneChat(alice.getId(), bob.getId());

        // Alice sends a message to Bob
        System.out.println("Alice sends a message...");
        chatService.sendMessage(alice.getId(), aliceBobChat.getId(), "Hi Bob, how are you?");

        // Bob sends a reply
        System.out.println("\nBob sends a reply...");
        chatService.sendMessage(bob.getId(), aliceBobChat.getId(), "I'm good, Alice! Thanks for asking.");
        System.out.println();

        // 4. Scenario 2: Group chat
        System.out.println("--- Starting a group chat for a 'Project Team' ---");
        List<String> projectMembers = List.of(alice.getId(), bob.getId(), charlie.getId());
        Chat projectGroup = chatService.createGroupChat("Project Team", projectMembers);

        // Charlie sends a message to the group
        System.out.println("Charlie sends a message to the group...");
        chatService.sendMessage(charlie.getId(), projectGroup.getId(), "Hey team, when is our deadline?");

        // Alice replies to the group
        System.out.println("\nAlice replies to the group...");
        chatService.sendMessage(alice.getId(), projectGroup.getId(), "It's next Friday. Let's sync up tomorrow.");
        System.out.println();

        // 5. Demonstrate fetching chat history
        System.out.println("--- Fetching Chat Histories ---");

        // History of Alice and Bob's chat
        System.out.println("\nHistory for chat '" + aliceBobChat.getName(alice) + "':");
        List<Message> oneToOneHistory = chatService.printChatHistory(aliceBobChat.getId());
        oneToOneHistory.forEach(System.out::println);

        // History of the project group chat
        System.out.println("\nHistory for chat '" + projectGroup.getName(charlie) + "':");
        List<Message> groupHistory = chatService.printChatHistory(projectGroup.getId());
        groupHistory.forEach(System.out::println);

        // 6. Demonstrate finding all of a user's chats
        System.out.println("\n--- Fetching all of Alice's chats ---");
        List<Chat> aliceChats = chatService.getUserChats(alice.getId());
        for(Chat chat : aliceChats) {
            System.out.println("Chat: " + chat.getName(alice) + " (ID: " + chat.getId() + ")");
        }
    }
}
