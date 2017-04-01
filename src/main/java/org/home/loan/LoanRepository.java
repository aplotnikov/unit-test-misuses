package org.home.loan;

import org.home.loan.domain.Loan;

public interface LoanRepository {

    Loan save(Loan loan);

    Loan findOne(Long id);

}
