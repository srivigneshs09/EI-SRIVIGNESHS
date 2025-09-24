package singleton;

import java.util.LinkedList;
import java.util.Queue;

public class PrinterSpooler {
    private static PrinterSpooler instance;
    private Queue<String> printJobs;

    // Private constructor to prevent instantiation
    private PrinterSpooler() {
        printJobs = new LinkedList<>();
    }

    // Thread-safe Singleton using synchronized
    public static synchronized PrinterSpooler getInstance() {
        if (instance == null) {
            instance = new PrinterSpooler();
            System.out.println("PrinterSpooler instance created.");
        }
        return instance;
    }

    public void addJob(String document) {
        printJobs.offer(document);
        System.out.println("Added to spooler: " + document);
    }

    public void printJob() {
        if (printJobs.isEmpty()) {
            System.out.println("No jobs to print.");
        } else {
            String job = printJobs.poll();
            System.out.println("Printing: " + job);
        }
    }
}
