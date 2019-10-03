package com.sapient.ch6;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Authored by: Pawan Kumar
 * Project: rxjava
 * Date: 03/Oct/2019
 * Time: 11:45 AM
 */
public class RxOperators {

    private static void example1() {
        Observable.just("one", "two", "three", "four")
                .map(String::toUpperCase)
                .subscribe(System.out::println);

        //maintaining time complexity of the underlying data source
    }

    private static void example2() {
        Observable.just("one", "two", "three", "four")
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("t"))
                .subscribe(System.out::println);
    }

    private static void example3() {
        Observable<Integer> obs = Observable.range(0, 100);
        obs.take(5)
                .subscribe(System.out::println); //prints 0 to 4

        obs.takeLast(1)
                .subscribe(System.out::println); //prints 99

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Observable<Integer> obs2 = Observable.fromIterable(numbers);

        System.out.println("*******");
        obs2.takeUntil(number -> number > 2)
                .subscribe(System.out::println); //prints 1 to 3

        obs2.takeWhile(number -> number < 4)
                .subscribe(System.out::println); //prints 1 to 3

        obs2.takeWhile(number -> number > 2)
                .subscribe(System.out::println); //prints nothing
    }

    //until the first observable isn’t done it won’t move to the second
    private static void concat() throws InterruptedException {
        Observable.concat(
                Observable.interval(1, TimeUnit.SECONDS).map(id -> "CA" + id),
                Observable.interval(1, TimeUnit.SECONDS).map(id -> "CB" + id))
                .subscribe(System.out::println);

        Thread.sleep(6000);
    }

    private static void concat2() throws InterruptedException {
        Observable.concat(
                Observable.interval(1, TimeUnit.SECONDS).take(2).map(id -> "CA" + id),
                Observable.interval(1, TimeUnit.SECONDS).map(id -> "CB" + id))
                .subscribe(System.out::println);

        Thread.sleep(6000);
    }

    public static void main(String[] args) throws InterruptedException {
        concat2();
    }
}
