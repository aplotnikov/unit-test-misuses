package org.home.notification;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
public class NotificationService {

    NotificationConfiguration configuration;

    NotificationPolicy policy;

    NotificationSender sender;

    public void send(Notification notification) {
        if (configuration.isAllowedToSendNotification() && policy.accept(notification)) {
            sender.send(notification);
        }
    }

}
