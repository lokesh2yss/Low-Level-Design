package chat_application.entities;

import java.util.List;

public class OneToOneChat extends Chat{
    public OneToOneChat(User user1, User user2) {
        super();
        this.members.addAll(List.of(user1, user2));
    }
    @Override
    public String getName(User perspectiveUser) {
        return members.stream()
                .filter(member -> !member.equals(perspectiveUser))
                .findFirst()
                .map(User::getName)
                .orElse("Uknown Char");
    }
}
