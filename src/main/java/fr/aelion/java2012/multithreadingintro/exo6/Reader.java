package fr.aelion.java2012.multithreadingintro.exo6;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Reader implements Runnable {
    int lineReaded = 0;
    final Scanner scanner;
    final OutputWriter writer;

    Reader(Scanner scanner, OutputWriter writer) {
        this.scanner = scanner;
        this.writer = writer;
    }

    public void run() {
        boolean hasNext = true;
        while (hasNext) {
            System.out.println("read with " + Thread.currentThread().getName());
            hasNext = false;
            synchronized (scanner) {
                if (scanner.hasNext()) {
                    hasNext = true;
                    String line = scanner.nextLine();
                    handleLine(line);
                    ++lineReaded;
                }
            }
        }
    }

    public String handleLine(String line) {
        return "writed by " + Thread.currentThread().getName() + " : " + line;
    }

    public synchronized void writeLine(String line) {
        writer.writeLine(line);
    }
}
