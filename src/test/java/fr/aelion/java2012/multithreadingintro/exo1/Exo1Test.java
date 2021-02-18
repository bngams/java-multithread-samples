package fr.aelion.java2012.multithreadingintro.exo1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exo1Test {
    @Test
    void testCounters() {
        System.out.println(Thread.currentThread().getName());

        List<Runnable> tasks = new ArrayList<>();
        tasks.add(new Counter("C1"));
        tasks.add(new Counter("C2"));
        tasks.add(new Counter("C3"));

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        tasks.forEach(executorService::submit);

        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }
}
