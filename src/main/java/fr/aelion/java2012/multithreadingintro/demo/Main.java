package fr.aelion.java2012.multithreadingintro.demo;

import java.net.http.HttpClient;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {



        System.out.println("Exo5BisMain thread :" + Thread.currentThread().getName());

        MySimpleThread t1 = new MySimpleThread();
        t1.start();

        MySimpleRunnable simpleRunnable = new MySimpleRunnable();
        Thread t2 = new Thread(simpleRunnable);
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside thread (lambda) : " + Thread.currentThread().getName());
            }
        }).start();

        new Thread(() -> {
            System.out.println("Inside thread (lambda) : " + Thread.currentThread().getName());
        }).start();



        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            System.out.println("Inside thread (lambda + executors) : " + Thread.currentThread().getName());
        });

        Future<String> result = executorService.submit(() -> {
            Thread.sleep(2000);
            return "Hello";
        });

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
