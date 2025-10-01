package notification_system.services;

import notification_system.entities.Notification;
import notification_system.entities.NotificationFactory;
import notification_system.entities.RetryableGatewayDecorator;
import notification_system.entities.interfaces.NotificationGateway;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotificationService {
    private final ExecutorService executor;
    public NotificationService(int poolSize) {
        this.executor = Executors.newFixedThreadPool(poolSize);
    }
    public void sendNotification(Notification notification) {
        executor.submit(() -> {
            NotificationGateway gateway = new RetryableGatewayDecorator(NotificationFactory.createGateway(notification.getType()), 3, 1000);
            try {
                gateway.send(notification);
            } catch (Exception e) {
                System.out.println("Exception while sending notification " + e.getMessage());
            }
        });
    }
    public void shutdown() {
        executor.shutdown();
    }

}
