package com.sapient.ch1;

import com.sapient.ch1.Txn.Buyer;

import java.util.*;

/**
 * Print the names of buyers in transactions over age 65, sorted by name
 */
public class ImperativeStyle {

    private static void imperative(List<Txn> transactions) {
        Set<Buyer> buyers = new HashSet<>(); // unnecessary declaration of this mid-level variable which is only used for computation
        for (Txn t : transactions) {
            if (t.getBuyer().getAge() >= 65)
                buyers.add(t.getBuyer());
        }
        List<Buyer> seniorBuyers = new ArrayList<>(buyers);
        seniorBuyers.sort(Comparator.comparing(Buyer::getName));
        for (Buyer b : seniorBuyers)
            System.out.println(b.getName());
    }

}
