package org.home.loan;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.home.loan.domain.Distribution;
import org.home.loan.domain.Loan;
import org.home.loan.domain.LoanApplication;

import java.math.BigDecimal;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.home.loan.utils.BigDecimalUtils.amount;

@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class LoanService {

    LoanRepository repository;

    public Loan findLoanBy(Long id) {
        return repository.findOne(id);
    }

    public Loan createLoanFrom(LoanApplication application) {
        Loan loan = new Loan();
        loan.setAmount(application.getAmount());
        loan.setTerm(application.getTerm());
        loan.setClientId(application.getClientId());

        return repository.save(loan);
    }

    public void update(Loan loan) {
        throw new NullPointerException("This method does not work");
    }

    public void process(Loan loan) {
        repository.save(loan);
    }

    public List<Distribution> findDistributionByLoanId(Long id) {
        return repository.findOne(id).getDistributions();
    }

    public BigDecimal calculateCommisionFor(Loan loan) {
        return loan.getAmount().multiply(amount(0.1));
    }

}
