package com.sapient.ch4;

import io.reactivex.Observable;

/**
 * Authored by: Pawan Kumar
 * Project: rxjava
 * Date: 02/Oct/2019
 * Time: 11:14 PM
 */
public class CompliantObservable {

    private static void compliant() {
        Observable.create(s -> new Thread(() -> {
            s.onNext("one");
            s.onNext("two");
            s.onNext("three");
            s.onNext("four");
            s.onComplete();
        }).start())
                .subscribe(s -> {
                    System.out.println("Subscriber thread: " + Thread.currentThread().getName());
                    System.out.println(s);
                });
    }

    public static void main(String[] args) {
        System.out.println("Main thread: " + Thread.currentThread().getName());
        compliant();
    }
}
