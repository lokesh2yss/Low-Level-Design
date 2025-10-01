package notification_system.entities;

import notification_system.enums.NotificationType;

import javax.sound.midi.Receiver;
import java.util.Objects;
import java.util.UUID;

public class Notification {
    private final String id;
    private final Recipient recipient;
    private final NotificationType type;
    private final String message;
    private final String subject;

    public Notification(Builder builder) {
        this.id = UUID.randomUUID().toString();
        this.recipient = builder.recepient;
        this.type = builder.type;
        this.message = builder.message;
        this.subject = builder.subject;
    }

    public String getId() {
        return id;
    }

    public NotificationType getType() {
        return type;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getSubject() {
        return subject;
    }
    public static class Builder {
        private final Recipient recepient;
        private final NotificationType type;
        private String message;
        private String subject;

        public Builder(Recipient recipient, NotificationType type) {
            this.recepient = recipient;
            this.type = type;
        }
        public Builder message(String message) {
            this.message = message;
            return this;
        }
        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }
        public Notification build() {
            return new Notification(this);
        }
    }
}
