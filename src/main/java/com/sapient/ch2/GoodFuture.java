package com.sapient.ch2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Authored by: Pawan Kumar
 * Project: rxjava
 * Date: 02/Oct/2019
 * Time: 7:30 PM
 */
public class GoodFuture {

    private static void executeTwoTasks() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<String> f1 = executor.submit(() -> {
            Thread.sleep(5000);
            return "responseA";
        });

        Future<String> f2 = executor.submit(() -> {
            Thread.sleep(2000);
            return "responseB";
        });

        System.out.println(f1.get() + " - " + f2.get());
    }

    public static void main(String[] args) throws Exception {
        executeTwoTasks();
    }

}
