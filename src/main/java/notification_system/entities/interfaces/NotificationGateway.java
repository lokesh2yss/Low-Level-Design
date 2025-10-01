package notification_system.interfaces;

import notification_system.entities.Notification;

public interface NotificationGateway {
    void send(Notification notification) throws Exception;
}
