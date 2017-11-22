package org.home.loan.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Distribution {

    private BigDecimal amount;

    private Date date;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Distribution that = (Distribution) o;
        return Objects.equals(amount, that.amount)
                && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, date);
    }

    @Override
    public String toString() {
        return "Distribution{" +
                "amount=" + amount +
                ", date=" + date +
                '}';
    }
}
