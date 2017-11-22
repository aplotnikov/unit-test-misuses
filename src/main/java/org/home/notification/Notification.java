package org.home.notification;

import org.home.client.Client;

import java.util.Objects;

class Notification {

    private String message;

    private Client to;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Client getTo() {
        return to;
    }

    public void setTo(Client to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Notification that = (Notification) o;
        return Objects.equals(message, that.message) &&
                Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, to);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "message='" + message + '\'' +
                ", to=" + to +
                '}';
    }
}
