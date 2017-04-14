package org.home.notification;

interface NotificationPolicy {

    boolean accept(Notification notification);

}
