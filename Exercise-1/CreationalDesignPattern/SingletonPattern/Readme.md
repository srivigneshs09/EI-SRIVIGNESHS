# Singleton Pattern – Printer Spooler

## Overview
This project demonstrates the **Singleton design pattern** in Java using a **Printer Spooler** as an example.  
The Singleton ensures that only **one instance of the PrinterSpooler exists** throughout the application, avoiding conflicts when multiple clients add or process print jobs.

---

## Structure
- **PrinterSpooler (Singleton)**
    - Manages a queue of print jobs.
    - Provides methods `addJob()` and `printJob()`.
    - Ensures only one instance exists.

- **Main (Client)**
    - Demonstrates how multiple objects share the same Singleton instance.

---

## Class Diagram
![Class Diagram.png](OutputScreenShot/Class%20Diagram.png)

---

## Output Screenshots

- **Printer Instance Creation**

![InstanceCreation.png](OutputScreenShot/InstanceCreation.png)

- **Proof of Single Instance**

![ProofOfSingleInstance.png](OutputScreenShot/ProofOfSingleInstance.png)

- **Adding Job to Printer Instance**

![AddingJob.png](OutputScreenShot/AddingJob.png)

- **Printing the Jobs**

![PrintingJobs.png](OutputScreenShot/PrintingJobs.png)