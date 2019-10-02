package com.sapient.ch1;

import com.sapient.ch1.Txn.Buyer;

import java.util.List;

import static java.util.Comparator.comparing;

/**
 * Print the names of buyers in transactions over age 65, sorted by name
 */
public class DeclarativeStyle {

    private void declarative(List<Txn> transactions) {

        transactions.stream()
                .filter(t -> t.getBuyer().getAge() >= 65)
                .map(Txn::getBuyer)
                .distinct()
                .sorted(comparing(Buyer::getName))
                .map(Buyer::getName)
                .forEach(System.out::println);
    }
}
