package org.home.loan.domain;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class LoanApplication {

    Long clientId;

    BigDecimal amount;

    Term term;
}
