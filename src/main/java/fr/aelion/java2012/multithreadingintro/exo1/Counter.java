package fr.aelion.java2012.multithreadingintro.exo1;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Counter implements Runnable {
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
            TimeUnit.SECONDS.sleep(new Random().nextInt(3));
            System.out.println(this.name + " is counting " + i);
        }
        System.out.println(this.name + " counting done");
    }
}
