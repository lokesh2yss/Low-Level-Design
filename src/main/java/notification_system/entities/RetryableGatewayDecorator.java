package notification_system.entities;

import notification_system.entities.interfaces.NotificationGateway;

public class RetryableGatewayDecorator implements NotificationGateway {
    private final NotificationGateway wrappedGateway;
    private final int maxRetries;
    private final long retryDelayMillis;

    public RetryableGatewayDecorator(NotificationGateway wrappedGateway, int maxRetries, long retryDelayMillis) {
        this.wrappedGateway = wrappedGateway;
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
    }

    @Override
    public void send(Notification notification) throws Exception {
        int attempt = 0;
        while(attempt < maxRetries) {
            try {
                wrappedGateway.send(notification);
                return;
            }
            catch (Exception e) {
                attempt++;
                System.out.println("Error: Attempt " + attempt + " failed for notification " + notification.getId() + ". Retrying...");
                if(attempt >= maxRetries) {
                    System.out.println(e.getMessage());
                    throw new Exception("Failed to send notification after " + maxRetries + " attempts.", e);
                }
                Thread.sleep(retryDelayMillis);
            }
        }
    }
}
