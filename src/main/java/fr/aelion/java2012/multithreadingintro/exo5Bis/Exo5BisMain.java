package fr.aelion.java2012.multithreadingintro.exo5Bis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Exo5BisMain {
    public static void main(String[] args) {
        System.out.println("launch program");
        System.out.println("===== Read lines");

        try {
            // creer le printer
            PrintWriter pw = new PrintWriter("outFile.txt");
            // lecture fichier
            Runnable reader1 = new Runnable() {
                @Override
                public void run() {
                    String fileName = "file1.txt";
                    try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                        stream.forEach((l) ->{
                            pw.println(l);
                            pw.flush();
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            new Thread(reader1).start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
