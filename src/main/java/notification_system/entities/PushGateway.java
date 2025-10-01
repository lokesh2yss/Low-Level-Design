package notification_system.entities;

import notification_system.entities.interfaces.NotificationGateway;

public class PushGateway implements NotificationGateway {
    @Override
    public void send(Notification notification) throws Exception {
        String pushToken = notification.getRecipient().getPushToken()
                .orElseThrow(() -> new IllegalArgumentException("pushToken is required for a push notification"));
        System.out.println("--- Sending Push Notification---");
        System.out.println("To device token: "+pushToken);
        System.out.println("Title: "+ notification.getSubject());
        System.out.println("Body :"+notification.getMessage());
        System.out.println("-----------------------------------");
    }
}
