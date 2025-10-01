package notification_system.entities.interfaces;

import notification_system.entities.Notification;

public interface NotificationGateway {
    void send(Notification notification) throws Exception;
}
