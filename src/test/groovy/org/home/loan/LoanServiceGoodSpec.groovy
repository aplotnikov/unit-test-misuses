package org.home.loan

import static org.home.loan.domain.Term.days

import org.home.loan.domain.Distribution
import org.home.loan.domain.Loan
import org.home.loan.domain.LoanApplication
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class LoanServiceGoodSpec extends Specification {

    @Shared
    Long clientId = 1

    @Shared
    Long loanId = 1

    LoanRepository repository = Mock(LoanRepository)

    @Subject
    LoanService service = new LoanService(repository)

    LoanApplication application = Stub(LoanApplication) {
        getAmount() >> 1000
        getTerm() >> days(30)
        getClientId() >> clientId
    }

    Loan loan = Stub {
        getId() >> loanId
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
            result.amount == application.amount
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

    void 'loan distributions amount should zero'() {
        given:
            repository.findOne(loanId) >> loanWith([new Distribution(amount: 30)])
        when:
            List<Distribution> distributions = service.findDistributionByLoanId(loanId)
        then:
            distributions.findAll { it.amount > 0 }.size() == 1
    }

    void 'loan distributions amount should zero2'() {
        given:
            Distribution distribution = new Distribution(amount: 30)
        and:
            repository.findOne(loanId) >> loanWith([distribution])
        when:
            List<Distribution> distributions = service.findDistributionByLoanId(loanId)
        then:
            distributions.findAll { it.amount > 0 } == [distribution]
    }

    private static Loan loanWith(List<Distribution> distributions) {
        return new Loan(distributions: distributions)
    }

    private LoanApplication application() {
        return new LoanApplication(amount: 1000, term: days(30), clientId: clientId)
    }

}
