package org.home.loan.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class LoanApplication {

    private Long clientId;

    private BigDecimal amount;

    private Term term;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LoanApplication that = (LoanApplication) o;
        return Objects.equals(clientId, that.clientId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(term, that.term);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, amount, term);
    }

    @Override
    public String toString() {
        return "LoanApplication{" +
                "clientId=" + clientId +
                ", amount=" + amount +
                ", term=" + term +
                '}';
    }
}
