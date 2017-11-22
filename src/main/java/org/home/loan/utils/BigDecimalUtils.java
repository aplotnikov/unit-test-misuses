package org.home.loan.utils;

import java.math.BigDecimal;

public final class BigDecimalUtils {

    private BigDecimalUtils() {
    }

    public static BigDecimal amount(String amount) {
        return amount(Double.valueOf(amount));
    }

    public static BigDecimal amount(double amount) {
        return BigDecimal.valueOf(amount);
    }
}
