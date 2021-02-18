package fr.aelion.java2012.multithreadingintro.demo;

public class MySimpleThread extends Thread {
    @Override
    public void run() {
        System.out.println("Inside thread : " + Thread.currentThread().getName());
    }
}
