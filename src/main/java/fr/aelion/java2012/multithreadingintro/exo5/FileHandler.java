package fr.aelion.java2012.multithreadingintro.exo5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileHandler {

    public static void handleFileNIO(File f, OutputWriter outputWriter) {
        System.out.println("Handled by " + Thread.currentThread().getName());
        try (Stream<String> stream = Files.lines(Paths.get(f.getAbsolutePath()))) {
            stream.forEach(outputWriter::writeLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
