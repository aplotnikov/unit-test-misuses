package org.home.loan;

import org.home.loan.domain.Loan;
import org.home.loan.domain.LoanApplication;

public interface LoanRepository {

    Loan save(Loan loan);

    Loan save(LoanApplication application);

    Loan findOne(Long id);

}
