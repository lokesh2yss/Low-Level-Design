package notification_system.entities;

import notification_system.entities.interfaces.NotificationGateway;
import notification_system.enums.NotificationType;

import java.util.HashMap;
import java.util.Map;

public class NotificationFactory {
    private static final Map<NotificationType, NotificationGateway> notificationMap = new HashMap<>();
    public static NotificationGateway createGateway(NotificationType type) {
        if(notificationMap.containsKey(type)) {
            return notificationMap.get(type);
        }
        NotificationGateway gateway = switch (type) {
            case EMAIL -> new EmailGateway();
            case SMS -> new SmsGateway();
            case PUSH -> new PushGateway();
        };
        notificationMap.put(type, gateway);
        return gateway;
    }
}
