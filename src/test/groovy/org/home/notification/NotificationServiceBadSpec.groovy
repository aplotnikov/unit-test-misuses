package org.home.notification

import spock.lang.Specification
import spock.lang.Subject

class NotificationServiceBadSpec extends Specification {

    NotificationConfiguration configuration = Stub(NotificationConfiguration)

    NotificationPolicy policy = Stub(NotificationPolicy)

    NotificationSender sender = Mock(NotificationSender)

    @Subject
    NotificationService service = new NotificationService(configuration, policy, sender)

    Notification notification = new Notification()

    def "should send notification"() {
        given:
            configuration.isAllowedToSendNotification() >> configurationState
        and:
            policy.accept(notification) >> policyState
        where:
            configurationState | policyState
            false              | false
            false              | true
            true               | false
            true               | true
    }
}
