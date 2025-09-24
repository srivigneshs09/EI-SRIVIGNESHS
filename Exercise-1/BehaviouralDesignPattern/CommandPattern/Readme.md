# Command Pattern – Remote Control

## Overview
This project demonstrates the **Command Design Pattern** in Java.  
The pattern encapsulates a request as an object, allowing:
- Parameterization of clients with different requests
- Queuing and logging of requests
- Support for undo operations

In this example, a **remote control** can turn a **Light ON/OFF** and also **undo the last action**.

---

## Structure
- **Command** → Interface with `execute()` and `undo()`
- **LightOnCommand / LightOffCommand** → Concrete command classes to control the Light
- **Light** → Receiver class performing actual ON/OFF operations
- **RemoteControl** → Invoker that executes and undoes commands
- **Main** → Client that interacts with the system through a simple menu

---

## Class Diagram
![ClassDiagram.png](OutputScreenShot/ClassDiagram.png)

---

## Output Screenshots

- **Turn On Light** 
![1.png](OutputScreenShot/1.png)

- **Clicking Turn On Light Again**
![2.png](OutputScreenShot/2.png)

- **Turn Off Light**
![3.png](OutputScreenShot/3.png)

- **Undo Operation**
![4.png](OutputScreenShot/4.png)

- **Exiting**
![5.png](OutputScreenShot/5.png)