package com.sapient.ch4;

import io.reactivex.Observable;

/**
 * Authored by: Pawan Kumar
 * Project: rxjava
 * Date: 02/Oct/2019
 * Time: 11:17 PM
 */
public class NonCompliantObservable {

    private static void nonCompliant() {
        Observable.create(s -> {
            // Thread A
            new Thread(() -> {
                s.onNext("one");
                s.onNext("two");
            }).start();

            // Thread B
            new Thread(() -> {
                s.onNext("three");
                s.onNext("four");
            }).start();

            // ignoring need to emit s.onComplete() due to race of threads
        });
    }
}
