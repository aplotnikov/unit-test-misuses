package org.home

import static org.home.loan.utils.BigDecimalUtils.amount

import org.home.loan.LoanRepository
import org.home.loan.LoanService
import org.home.loan.domain.Loan
import org.home.loan.domain.LoanApplication
import org.home.loan.domain.Term
import org.junit.Assert
import spock.lang.Specification
import spock.lang.Unroll

public class BadSpec extends Specification {

    static final CLIENT_ID = 1;

    LoanRepository repository = Mock();

    def service = new LoanService(repository);

    LoanApplication application

    Loan loan

    def setup() {
        application = Mock()
        loan = Mock()
        application.getAmount() >> new BigDecimal(1000);
        loan.getAmount() >> amount("1000")
        application.getTerm() >> new Term(30)
        application.getClientId() >> CLIENT_ID
    }

    @Unroll
    def "should create new loan from the application"() {
        given:
            repository.save(_) >> { it[0] }
        when:
            def result = service.createLoanFrom(application)
        then:
            result.amount == application.amount;
            result.clientId == application.clientId
            result.term == application.term
        where:
            amount             | term         || expectedResult
            new BigDecimal(10) | new Term(20) || true
            new BigDecimal(30) | new Term(10) || false
    }

    public void shouldCreateNewLoanFromApplication() {
        Loan result = service.createLoanFrom(application)

        Assert.assertEquals(result.amount, application.getAmount())
    }

    def "should thrown NullPointerException when loan is null"() {
        when:
            service.update(null)
        then:
            thrown(NullPointerException.class)
    }

    def "should process loan"() {
        given:
            List<Integer> distributions = new ArrayList<>()
            distributions.add(1)
            distributions.add(2)
            distributions.add(3)
            Loan loan = loanWith(distributions)
        when:
            service.process(loan)
        then:
            1 * repository.save(loan)
    }

    def loanWith(distributions) {
        Loan loan = Mock()
        loan.getDistributions() >> distributions
        return loan;
    }

    def application() {
        LoanApplication application = Stub()
        application.getAmount() >> new BigDecimal(1000)
        application.getTerm() >> new Term(30);
        application.getClientId() >> CLIENT_ID
        return application;
    }
}
