package com.sapient.ch5;

import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

/**
 * Authored by: Pawan Kumar
 * Project: rxjava
 * Date: 03/Oct/2019
 * Time: 10:27 AM
 */
public class CreatingSubjects {


    private static void publishSubject() { //from the point when subscription starts
        PublishSubject<Integer> source = PublishSubject.create();

        // It will get 1, 2, 3, 4 and onComplete
        source.subscribe(System.out::println);

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        // It will get 4 and onComplete for second observer also.
        source.subscribe(System.out::println);

        source.onNext(4);
        source.onComplete();
    }

    private static void replaySubject() {//emmit all regardless of when subscription was added
        ReplaySubject<Integer> source = ReplaySubject.create();

        // It will get 1, 2, 3, 4
        source.subscribe(System.out::println);
        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
        source.onNext(4);
        source.onComplete();

        // It will also get 1, 2, 3, 4 as we have used replay Subject
        source.subscribe(System.out::println);
    }

    private static void behaviorSubject() {//emmit last item just before subscription and all forthcoming emitted items
        BehaviorSubject<Integer> source = BehaviorSubject.create();

        // It will get 1, 2, 3, 4 and onComplete
        source.subscribe(System.out::println);
        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        // It will get 3(last emitted)and 4(subsequent item) and onComplete
        source.subscribe(System.out::println);
        source.onNext(4);
        source.onComplete();
    }

    private static void asyncSubject() {//only get the last emitted value for each subscription
        AsyncSubject<Integer> source = AsyncSubject.create();

        // It will get only 4 and onComplete
        source.subscribe(System.out::println);
        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        // It will also get only get 4 and onComplete
        source.subscribe(System.out::println);
        source.onNext(4);
        source.onComplete();
    }
}
