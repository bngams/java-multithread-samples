package fr.aelion.java2012.multithreadingintro.exo6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class OutputWriter {
    private File f;
    private PrintWriter writer;

    public OutputWriter(File f) throws FileNotFoundException, UnsupportedEncodingException {
        this.f = f;
        this.writer = new PrintWriter(f, "UTF-8");
    }

    /**
     * This is JAVADOC
     * @param String s
     * @return void
     */
    public synchronized void writeLine(String s) {
        this.writer.println(s);
        this.writer.flush();
    }

    /**
     * This is JAVADOC
     * @return void
     */
    public void close() {
        this.writer.close();
    }

    @Override
    public String toString() {
        return "OutputWriter{" +
                "f=" + f +
                '}';
    }
}
