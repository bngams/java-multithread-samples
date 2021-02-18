package fr.aelion.java2012.multithreadingintro.exo6;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        try {
            createFile();

            // JAVA NIO
            // read from a line
            // Stream<String> lines = Files.lines(Paths.get("src/main/resources/big-file.txt"));
            // skip lines
            // Stream<String> linesToHandle = lines.skip(250);
            // get a specific line
            // String line250 = lines.skip(250).findFirst().get();

            // output file writer
            OutputWriter outWriter = new OutputWriter(new File("src/main/resources/file-output.txt"));

            // file reader with Scanner
            Scanner scanner = new Scanner(new File("src/main/resources/big-file.txt"));

            readAndWriteBasic(scanner, outWriter);
            // read file
            // int nbReaders = 3;
            // ExecutorService executorService = Executors.newFixedThreadPool(nbReaders);
            // for (int i = 0; i < nbReaders; i++) {
            //     executorService.submit(new Reader(scanner, outWriter));
            // }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readAndWriteBasic(Scanner scanner, OutputWriter writer) {
        // read file
        int nbReaders = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(nbReaders);
        while (scanner.hasNext()) {
            System.out.println("read with " + Thread.currentThread().getName());
            String line = scanner.nextLine();
            Callable<String> c = () -> { return LineHandler.handleLine(line); };
            Future<String> r = executorService.submit(c);
            try {
                writer.writeLine(r.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    static void createFile() {
        try {
            PrintWriter myWriter = new PrintWriter("src/main/resources/big-file.txt");
            for (int i = 0; i < 1000 ; i++) {
                myWriter.println("File 1 - line #" + i);
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
