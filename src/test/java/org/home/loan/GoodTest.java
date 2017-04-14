package org.home.loan;

import org.home.loan.domain.Loan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.home.loan.utils.BigDecimalUtils.amount;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GoodTest {

    @InjectMocks
    private LoanService service;

    @Test
    public void shouldCalculateCommissionForLoan() {
        Loan loan = new Loan();
        loan.setAmount(amount(15));

        BigDecimal commission = service.calculateCommissionFor(loan);

        assertThat(commission, comparesEqualTo(amount(1.5)));
    }

}
