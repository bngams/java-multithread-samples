package fr.aelion.java2012.multithreadingintro.exo5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {

    public static void handleFile(File f, OutputWriter outputWriter) {
        System.out.println("Handled by " + Thread.currentThread().getName());
        try {
            Scanner scanner = new Scanner(f);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // System.out.println("Read line is " + line);
                outputWriter.writeLine(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
