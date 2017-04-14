package org.home.notification;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.home.client.Client;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
class Notification {

    String message;

    Client to;

}
