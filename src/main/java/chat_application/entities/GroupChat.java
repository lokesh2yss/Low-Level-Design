package chat_application.entities;

import java.util.List;

public class GroupChat extends Chat{
    private final String groupName;
    public GroupChat(String groupName, List<User> initialMembers) {
        super();
        this.groupName = groupName;
        this.members.addAll(initialMembers);
    }
    public void addMember(User user) {
        if(!members.contains(user)) {
            members.add(user);
        }
    }
    public void removeMember(User user) {
        members.remove(user);
    }
    @Override
    public String getName(User perspectiveUser) {
        return groupName;
    }

}
