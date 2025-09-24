import singleton.PrinterSpooler;

public class Main {
    public static void main(String[] args) {
        // Try to get the spooler instance
        PrinterSpooler spooler1 = PrinterSpooler.getInstance();
        PrinterSpooler spooler2 = PrinterSpooler.getInstance();

        // Prove both references are the same
        System.out.println("spooler1 hash: " + spooler1.hashCode());
        System.out.println("spooler2 hash: " + spooler2.hashCode());

        // Add print jobs
        spooler1.addJob("DSA Assignment");
        spooler1.addJob("DBMS Project Report");
        spooler2.addJob("Cloud Computing Notes");

        // Process jobs
        spooler1.printJob();
        spooler2.printJob();
        spooler1.printJob();
        spooler1.printJob();
    }
}
