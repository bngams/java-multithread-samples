package fr.aelion.java2012.multithreadingintro.exo2bis;

public class Exo2Bis {

    public static void main(String[] args) {
        countWithCounterAndSync();
    }

    private static void countWithCounterAndSync() {
        Counter counter = new Counter();
        Runnable task = () -> {
            while(counter.getCount() < 100) {
                try {
                    Thread.sleep((long) Math.random()*10000);
                    counter.increment();
                    System.out.println(counter.getCount());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        int numberOfTasks = 3;
        for (int i = 0; i < 3; i++) {
            new Thread(task).start();
        }
    }

    private static void countWithAtomicCounter() {
        AtomicCounter counter = new AtomicCounter();
        Runnable task = () -> {
            while(counter.count.get() < 100) {
                try {
                    Thread.sleep((long) Math.random()*10000);
                    System.out.println(counter.count.incrementAndGet());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        int numberOfTasks = 3;
        for (int i = 0; i < 3; i++) {
            new Thread(task).start();
        }
    }
}
