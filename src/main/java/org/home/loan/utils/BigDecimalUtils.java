package org.home.loan.utils;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class BigDecimalUtils {

    public static BigDecimal amount(String amount) {
        return amount(Double.valueOf(amount));
    }

    public static BigDecimal amount(double amount) {
        return BigDecimal.valueOf(amount);
    }
}
