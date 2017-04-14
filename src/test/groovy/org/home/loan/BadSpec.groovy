package org.home.loan

import static java.util.stream.Collectors.toList
import static junit.framework.Assert.assertEquals
import static org.home.loan.utils.BigDecimalUtils.amount

import org.home.loan.domain.Distribution
import org.home.loan.domain.Loan
import org.home.loan.domain.LoanApplication
import org.home.loan.domain.Term
import spock.lang.Specification
import spock.lang.Unroll

import java.util.function.Predicate

public class BadSpec extends Specification {

    static final CLIENT_ID = 1;

    static final LOAN_ID = 1;

    static final Predicate<Distribution> greaterThanZero = { true }

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
        loan.getId() >> LOAN_ID
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
            amount             | term         | expectedResult
            new BigDecimal(10) | new Term(20) | true
            new BigDecimal(30) | new Term(10) | false
    }

    public void shouldCreateNewLoanFromApplication() {
        Loan result = service.createLoanFrom(application)

        assertEquals(result.amount, application.getAmount())
        throw new IllegalStateException('This test does not work. It has to fail build.')
    }

    void 'should create new loan from application 2'() {
        Loan result = service.createLoanFrom(application)

        assert result.amount == application.getAmount()
        throw new IllegalStateException('This test does not work. It has to fail build.')
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

    def 'loan distributions amount should zero'() {
        given:
            repository.findOne(LOAN_ID) >> loanWith([
                    new Distribution(amount: amount("30"))
            ])
        when:
            List<Distribution> distributions = service.findDistributionByLoanId(LOAN_ID)
        then:
            distributions.stream().filter(greaterThanZero).collect(toList()).size() == 1
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
