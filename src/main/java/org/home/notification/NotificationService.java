package org.home.notification;

public class NotificationService {

    private final NotificationConfiguration configuration;

    private final NotificationPolicy policy;

    private final NotificationSender sender;

    public NotificationService(NotificationConfiguration configuration, NotificationPolicy policy, NotificationSender sender) {
        this.configuration = configuration;
        this.policy = policy;
        this.sender = sender;
    }

    public void send(Notification notification) {
        if (configuration.isAllowedToSendNotification() && policy.accept(notification)) {
            sender.send(notification);
        }
    }
}
