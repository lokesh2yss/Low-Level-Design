package notification_system.entities;

import notification_system.entities.interfaces.NotificationGateway;

public class EmailGateway implements NotificationGateway {
    @Override
    public void send(Notification notification) throws Exception {
        String email = notification.getRecipient().getEmail()
                .orElseThrow(() -> new IllegalArgumentException("Email address is required for an email notification"));
        System.out.println("=== Sending Email---");
        System.out.println("To: "+email);
        System.out.println("Subject: "+notification.getSubject());
        System.out.println("Body: "+notification.getMessage());
        System.out.println("-----------------------------------");
    }
}
