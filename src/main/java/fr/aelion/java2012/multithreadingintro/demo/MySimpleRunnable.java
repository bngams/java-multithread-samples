package fr.aelion.java2012.multithreadingintro.demo;

public class MySimpleRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Inside thread (runnable) : " + Thread.currentThread().getName());
        doSomething();
    }

    private void doSomething() {
        System.out.println("Do something");
    }

}
