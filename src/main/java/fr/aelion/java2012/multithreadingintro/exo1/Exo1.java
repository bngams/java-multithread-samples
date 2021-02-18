package fr.aelion.java2012.multithreadingintro.exo1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exo1 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        List<Runnable> tasks = new ArrayList<>();
        tasks.add(new Counter("C1"));
        tasks.add(new Counter("C2"));
        tasks.add(new Counter("C3"));

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        tasks.forEach(executorService::submit);
    }
}
