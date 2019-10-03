package com.sapient.ch5;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Authored by: Pawan Kumar
 * Project: rxjava
 * Date: 02/Oct/2019
 * Time: 11:24 PM
 */
public class CreatingObservables {

    private static Observer encapsulatedObserver = new Observer<String>() {

        @Override
        public void onNext(String str) {
            System.out.println(str);
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onComplete() {
            System.out.println("onCompleteWasCalled");
        }

        @Override
        public void onSubscribe(Disposable d) {

        }
    };

    private static void emitRandomValues() {
        //completes itself after emitting two values
        var o = Observable.just("john", "doe");
        o.subscribeWith(encapsulatedObserver);
    }

    private static void fromIterable() {
        //completes itself after emitting two values
        String[] nameList = new String[]{"john", "doe"};
        var o = Observable.fromArray(nameList);
        o.subscribeWith(encapsulatedObserver);
    }

    private static void fromCreate() {
        var intObservable = intObservable();
        print("Starting");
        intObservable.subscribe(i -> print("Element: " + i));
        print("Exit");
    }

    private static void withConcurrency() {
        var asyncObservable = Observable.create(subscriber -> new Thread(() -> {
            try {
                subscriber.onNext("some data");
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).start());

        asyncObservable.subscribe(System.out::println);
    }

    private static void withConcurrencyUsingScheduler() {
        var asyncObservable = Observable.create(subscriber -> {
            try {
                subscriber.onNext("some data");
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
        asyncObservable.subscribeOn(Schedulers.io()).subscribe(System.out::println);
    }

    private static void multipleSubscribers() {
        var intObservable = intObservable();
        print("Starting");
        intObservable.subscribe(i -> print("Element A: " + i));
        intObservable.subscribe(i -> print("Element B: " + i));
        print("Exit");
    }

    private static void multipleSubscribersWithCache() {
        var intObservable = intObservable().cache();
        print("Starting");
        intObservable.subscribe(i -> print("Element A: " + i));
        intObservable.subscribe(i -> print("Element B: " + i));
        print("Exit");
    }

    private static Observable<Integer> intObservable() {
        Observable<Integer> o =
                Observable.create(subscriber -> {
                    print("Some expensive operation");
                    subscriber.onNext(5);
                    subscriber.onNext(6);
                    subscriber.onNext(7);
                    subscriber.onComplete();
                    print("Completed");
                });
        return o;
    }

    public static void main(String[] args) {
        multipleSubscribers();
    }

    private static void print(Object msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }

}
