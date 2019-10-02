package com.sapient.ch3;

import io.reactivex.Observable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Authored by: Pawan Kumar
 * Project: rxjava
 * Date: 02/Oct/2019
 * Time: 10:28 PM
 */
public class AsyncObservable {

    private static Map<String, String> cache = new HashMap<>();
    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    private static void asyncObservable() {
        Observable.create(s -> {
            var fromCache = cache.get("non_existing_key");
            if (fromCache != null) {
                // emit synchronously
                s.onNext(fromCache);
                s.onComplete();
            } else {
                // fetch asynchronously
                CompletableFuture.supplyAsync(() -> {
                    delayExecution();
                    return "result";
                }).thenAccept(v -> {
                    cache.put("non_existing_key", v);
                    s.onNext(v);
                    s.onNext("John");
                    s.onNext("Doe");
                    s.onComplete();
                });

            }
        }).subscribe(s -> System.out.println(s));
        System.out.println("This should be printed immediately");
        delayMainThreadToPrintOutput();
    }

    private static void delayExecution() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }

    private static void delayMainThreadToPrintOutput() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
        asyncObservable();
    }

}
