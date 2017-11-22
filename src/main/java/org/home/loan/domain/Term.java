package org.home.loan.domain;

public class Term {

    private final int days;

    public Term(int days) {
        this.days = days;
    }

    public static Term days(int days) {
        return new Term(days);
    }
}
