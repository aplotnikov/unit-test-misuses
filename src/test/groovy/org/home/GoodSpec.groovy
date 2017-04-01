package org.home

import static org.home.loan.domain.Term.days

import org.home.loan.LoanRepository
import org.home.loan.LoanService
import org.home.loan.domain.Distribution
import org.home.loan.domain.Loan
import org.home.loan.domain.LoanApplication
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class GoodSpec extends Specification {

    @Shared
    Long clientId = 1

    LoanRepository repository = Mock(LoanRepository)

    @Subject
    LoanService service = new LoanService(repository)

    LoanApplication application = Stub(LoanApplication) {
        getAmount() >> 1000
        getTerm() >> days(30)
        getClientId() >> clientId
    }

    Loan loan = Stub(Loan) {
        getAmount() >> 1000
    }

    void 'should create new loan from the application'() {
        given:
            repository.save(_ as Loan) >> { Loan loan -> loan }
        when:
            Loan result = service.createLoanFrom(application)
        then:
            with(result) {
                amount == application.amount
                clientId == application.clientId
                term == application.term
            }
    }

    void 'should create new loan from application'() {
        given:
            repository.save(_ as Loan) >> { Loan loan -> loan }
        when:
            Loan result = service.createLoanFrom(application)
        then:
            result.amount == application.getAmount()
    }

    void 'should thrown NullPointerException when call update method'() {
        when:
            service.update(null)
        then:
            NullPointerException exception = thrown(NullPointerException)
            exception.message == 'This method does not work'
    }

    void 'should process loan'() {
        given:
            List<Distribution> distributions = [new Distribution(amount: 10)]
            Loan loan = loanWith(distributions)
        when:
            service.process(loan)
        then:
            1 * repository.save(loan)
    }

    private static Loan loanWith(List<Distribution> distributions) {
        return new Loan(distributions: distributions)
    }

}
