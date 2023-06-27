package fr.aelion.java2012.multithreadingintro.exo6;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.extern.java.Log;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.stream.Stream;

@Log
public class Main {

    public static void main(String[] args) {
            String inputPath = "src/main/resources/big-file.txt";
            String outputPath = "src/main/resources/file-output.txt";
            createFile(inputPath);

            readWithNIO(inputPath,outputPath);
            // readWithScanner(inputPath, outputPath);
    }

    /**
     *
     * @param inputPath
     * @param outputPath
     */
    static void readWithNIO(String inputPath, String outputPath) {
        try {
            // output file writer
            OutputWriter outWriter = new OutputWriter(new File(outputPath));

            // JAVA NIO
            // read from a line
            Files.lines(Paths.get(inputPath))
                    // .filter()
                    .parallel()
                    .forEach((line) -> {
                        outWriter.writeLine(LineHandler.handleLine(line));
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readAndWrite() {
        // split file strategy
        // eg skip lines
        // Stream<String> linesToHandle = lines.skip(250);
        // get a specific line
        // String line250 = lines.skip(250).findFirst().get();


        // use the scanner for each thread
        // file reader with Scanner
        // Scanner scanner = new Scanner(new File("src/main/resources/big-file.txt"));
        // readAndWriteBasic(scanner, outWriter);
        // read file
        // int nbReaders = 3;
        // ExecutorService executorService = Executors.newFixedThreadPool(nbReaders);
        // for (int i = 0; i < nbReaders; i++) {
        //     executorService.submit(new Reader(scanner, outWriter));
        // }
    }

    static void readWithScanner(String inputPath, String outputPath) {
        try {
            // file reader with Scanner
            Scanner scanner = new Scanner(new File(inputPath));

            // output file writer
            OutputWriter outWriter = new OutputWriter(new File(outputPath));

            // read file and handle with multiple threads
            int nbHandlers = 3;
            ExecutorService executorService = Executors.newFixedThreadPool(nbHandlers);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                executorService.submit(() -> {
                    outWriter.writeLine(LineHandler.handleLine(line));
                });
            }
            executorService.shutdown();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    static void createFile(String inputPath) {
        try {
            PrintWriter myWriter = new PrintWriter(inputPath);
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
