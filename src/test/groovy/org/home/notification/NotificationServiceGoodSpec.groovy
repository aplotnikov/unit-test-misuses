package org.home.notification

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class NotificationServiceGoodSpec extends Specification {

    NotificationConfiguration configuration = Stub(NotificationConfiguration)

    NotificationPolicy policy = Stub(NotificationPolicy)

    NotificationSender sender = Mock(NotificationSender)

    @Subject
    NotificationService service = new NotificationService(configuration, policy, sender)

    Notification notification = new Notification()

    @Unroll
    void 'should not send notification when configuration state is #configurationState and policy state is #policyState'() {
        given:
            configuration.isAllowedToSendNotification() >> configurationState
        and:
            policy.accept(notification) >> policyState
        when:
            service.send(notification)
        then:
            0 * sender.send(notification)
        where:
            configurationState | policyState
            false              | false
            false              | true
            true               | false
    }

    void 'should send notification'() {
        given:
            configuration.isAllowedToSendNotification() >> true
        and:
            policy.accept(notification) >> true
        when:
            service.send(notification)
        then:
            1 * sender.send(notification)
    }
}
