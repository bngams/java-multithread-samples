package fr.aelion.java2012.multithreadingintro.exo5;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        // demo of parallel on streams
        // parallelStrings();
        // parallelInts();
        // filesWithParallel();
        filesWithExecutor();
        System.exit(0);
    }

    public static void filesWithParallel() {
        try {
            // output file writer
            OutputWriter outWriter = new OutputWriter(new File("src/main/resources/exo5/output.txt"));
            Files.walk(Paths.get("C:\\Storage\\Formation\\JAVA-1220\\multithreadingintro\\src\\main\\resources\\exo5\\input"))
                    .parallel()
                    .peek(filePath -> System.out.println("processing "+filePath.getFileName()+" in "+Thread.currentThread().getName()))
                    //.peek(x -> LockSupport.parkNanos("simulated workload", TimeUnit.SECONDS.toNanos(3)))
                    .filter(Files::isRegularFile)
                    .forEach(filePath-> {
                        FileHandler.handleFile(filePath.toFile(), outWriter);
                    });
            outWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void filesWithExecutor() {
        try {
            ExecutorService ex = Executors.newFixedThreadPool(3);
            // output file writer
            OutputWriter outWriter = new OutputWriter(new File("src/main/resources/exo5/output.txt"));
            Files.walk(Paths.get("C:\\Storage\\Formation\\JAVA-1220\\multithreadingintro\\src\\main\\resources\\exo5\\input"))
                    .filter(Files::isRegularFile)
                    .forEach(filePath-> {
                        ex.submit(() -> FileHandler.handleFile(filePath.toFile(), outWriter));
                    });
            System.out.println("Stream done");
            // stop executor to accept other tasks
            ex.shutdown();
            ex.awaitTermination(15, TimeUnit.SECONDS);
            System.out.println("Executor done");
            outWriter.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void parallelStrings() {
        List<String> strings = Arrays.asList("a1", "a2", "b1", "c2", "c1");

        strings
                .parallelStream()
                .filter(
                        s -> {
                            System.out.format("filter:  %s [%s]\n", s, Thread.currentThread()
                                    .getName());
                            return true;
                        })
                .map(
                        s -> {
                            System.out.format("map:     %s [%s]\n", s, Thread.currentThread()
                                    .getName());
                            return s.toUpperCase();
                        })
                .sorted(
                        (s1, s2) -> {
                            System.out.format("sort:    %s <> %s [%s]\n", s1, s2, Thread
                                    .currentThread().getName());
                            return s1.compareTo(s2);
                        })
                .forEach(
                        s -> System.out.format("forEach: %s [%s]\n", s, Thread
                                .currentThread().getName()));
    }

    public static void parallelInts() {
        IntStream.range(0, 100)
                .parallel()
                .forEach((i)-> {
                    System.out.println(i);
                    System.out.println(Thread.currentThread().getName());
                });
    }
}
