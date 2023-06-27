package fr.aelion.java2012.multithreadingintro.exo2;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter implements Runnable {
    // thread safe type
    private static AtomicInteger rank = new AtomicInteger(0);
    private String name;
    private Integer max;

    public Counter(String name) {
        this(name, 10);
    }

    public Counter(String name, Integer max) {
        this.name = name;
        this.max = max;
    }

    @Override
    public void run() {
        System.out.println(this.name + " launched");
        try {
            this.count();
        } catch (InterruptedException e) {
            System.out.println(this.name + " thread interrupted");
        }
    }

    private void count() throws InterruptedException {
        for (int i = 0; i < this.max ; i++) {
            Thread.currentThread().sleep((int) Math.random() * 1000);
            System.out.println(this.name + " is counting " + i);
        }
        System.out.println(this.name + " counting done " + rank.incrementAndGet());
    }
}
