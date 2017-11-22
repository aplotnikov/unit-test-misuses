package org.home.loan.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Loan {

    private Long id;

    private Long clientId;

    private BigDecimal amount;

    private Term term;

    private List<Distribution> distributions;

    public Long getId() {
        return id;
    }

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

    public List<Distribution> getDistributions() {
        return distributions;
    }

    public void setDistributions(List<Distribution> distributions) {
        this.distributions = distributions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        if (!super.equals(o)) {
            return false;
        }

        Loan loan = (Loan) o;
        return Objects.equals(id, loan.id) &&
                Objects.equals(clientId, loan.clientId) &&
                Objects.equals(amount, loan.amount) &&
                Objects.equals(term, loan.term) &&
                Objects.equals(distributions, loan.distributions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, clientId, amount, term, distributions);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", amount=" + amount +
                ", term=" + term +
                ", distributions=" + distributions +
                '}';
    }
}
