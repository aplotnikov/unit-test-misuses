package org.home.loan.domain;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class Term {

    int days;

    public static Term days(int days) {
        return new Term(days);
    }
}
