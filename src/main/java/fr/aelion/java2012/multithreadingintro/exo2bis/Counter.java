package fr.aelion.java2012.multithreadingintro.exo2bis;

public class Counter {
    private Integer count = 0;

    public synchronized Integer getCount() {
        return this.count;
    }

    public synchronized void increment() {
        this.count += 1;
    }
}
