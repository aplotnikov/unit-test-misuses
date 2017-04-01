package org.home.loan.domain;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class Loan {

    Long id;

    Long clientId;

    BigDecimal amount;

    Term term;

    List<Distribution> distributions;
}
