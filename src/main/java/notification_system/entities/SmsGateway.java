package notification_system.entities;

import notification_system.entities.interfaces.NotificationGateway;

public class SmsGateway implements NotificationGateway {
    @Override
    public void send(Notification notification) throws Exception {
        String phone = notification.getRecipient().getPhoneNumber()
                .orElseThrow(() -> new IllegalArgumentException("Phone number is required for an SMS notification"));
        System.out.println("---Sending SMS---");
        System.out.println("To mobile number: "+phone);
        System.out.println("Message: "+notification.getMessage());
        System.out.println("-----------------------------------");
    }
}
