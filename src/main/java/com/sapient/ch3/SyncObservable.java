package com.sapient.ch3;

import io.reactivex.Observable;

import java.util.Map;

/**
 * Authored by: Pawan Kumar
 * Project: rxjava
 * Date: 02/Oct/2019
 * Time: 10:19 PM
 */
public class SyncObservable {

    private static Map<String, String> cache = Map.of("key1", "value1", "key2", "value2");

    private static void syncObservable() {
        Observable.create(s -> {
            s.onNext(cache.get("key1"));
            s.onNext(cache.get("key2"));
            s.onComplete();
        }).subscribe(s -> System.out.println(s));
        System.out.println("This should be printed afterwards");
    }

    public static void main(String[] args) {
        syncObservable();
    }
}
