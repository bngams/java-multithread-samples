package fr.aelion.java2012.multithreadingintro.exo6;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class LineHandler {

    public static String handleLine(String line) {
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("write with " + Thread.currentThread().getName());
        return "writed by " + Thread.currentThread().getName() + " : " + line;
    }
}
