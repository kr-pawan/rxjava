package com.sapient.ch5;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

/**
 * Authored by: Pawan Kumar
 * Project: rxjava
 * Date: 03/Oct/2019
 * Time: 8:09 AM
 */
public class Subscribing {

    private Observable<String> stringObservable = Observable.create(s -> {
        s.onNext("one");
        s.onNext("two");
        s.onNext("three");
        s.onNext("four");
        s.onComplete();
    });

    private void overloadedSubscriptions() {

        stringObservable.subscribe(str -> {
            //some implementation of onNext()
        });

        stringObservable
                .subscribe(str -> {
                    //some implementation of onNext()
                }, throwable -> {
                    //do something when error occurs
                });

        stringObservable
                .subscribe(str -> {
                    //some implementation of onNext()
                }, throwable -> {
                    //do something when error occurs
                }, () -> {
                    //onComplete takes an Action functional interface
                });

        stringObservable
                .subscribe(str -> {
                    //some implementation of onNext()
                }, throwable -> {
                    //do something when error occurs
                }, () -> {
                    //onComplete takes an Action functional interface
                }, disposable -> {
                    //used to cancel the subscription
                    disposable.dispose();
                });
    }

    private void useEncapsulatedObserver() {
        var encapsulatedObserver = new DisposableObserver<String>() {

            @Override
            public void onNext(String str) {
                System.out.println(str);
                if (str.equals("some text")) {
                    dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }

        };

        stringObservable.subscribeWith(encapsulatedObserver);
    }
}
