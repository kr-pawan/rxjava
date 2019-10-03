package com.sapient.ch4;

import io.reactivex.Observable;

/**
 * Authored by: Pawan Kumar
 * Project: rxjava
 * Date: 02/Oct/2019
 * Time: 11:18 PM
 */
public class CompliantMultipleObservables {

    private static void compliant() {
        Observable<String> firstObs = Observable.create(s -> new Thread(() -> {
            s.onNext("one");
            s.onNext("two");
            s.onComplete();
        }).start());

        Observable<String> secondObs = Observable.create(s -> new Thread(() -> {
            s.onNext("three");
            s.onNext("four");
            s.onComplete();
        }).start());
        // this subscribes to a and b concurrently,
        // and merges into a third sequential stream
        Observable<String> mergedObs = Observable.merge(firstObs, secondObs);
        mergedObs.subscribe(System.out::println);
    }

    public static void main(String[] args) {
        compliant();
    }
}
