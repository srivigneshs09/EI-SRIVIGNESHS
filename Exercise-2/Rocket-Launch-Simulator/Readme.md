# Rocket Launch Simulator

## **Project Overview**

The **Rocket Launch Simulator** is a terminal-based simulation of a rocket launch that takes a rocket from Earth to orbit. This project demonstrates **clean architecture**, **design patterns**, and **production-level coding practices** in Java.

The simulator allows users to:

* Perform **pre-launch checks**
* Launch a rocket with **stage-wise updates**
* Fast forward the simulation by a user-defined number of seconds
* View telemetry updates such as fuel, altitude, speed, and stage information

---

## **Features**

* **Menu-driven command system** for user input
* **State Pattern**: Manages different rocket stages (`PreLaunch`, `Stage1`, `Stage2`, `Orbit`, `MissionFailed`)
* **Command Pattern**: Each user command (`start_checks`, `launch`, `fast_forward`, `exit`) is encapsulated as a separate class
* **Observer Pattern**: Telemetry system notifies console and logger observers of rocket state changes
* **Singleton Pattern**: `MissionControl` ensures a single source of truth for the rocket and its state
* **Production-level practices**:

    * Input validation and defensive programming
    * Logging mechanism for telemetry and events
    * Exception handling and transient error handling
    * Optimized performance and clean, maintainable code

---

## **Rocket Parameters (Realistic Simulation)**

* **Total Fuel**: ~530 tonnes
* **Stage 1**:

    * Altitude: Up to 80 km
    * Speed: ~8,000 km/h
    * Burn time: ~2.5 minutes
* **Stage 2**:

    * Altitude: Up to 280 km (orbital)
    * Speed: ~28,000 km/h
    * Burn time: ~6 minutes
* **Total Flight Time**: ~8.5 minutes from Earth to orbit

---

## **Functional Requirements**

1. **Pre-Launch Checks**: Validate all systems are 'Go'
2. **Launch and Stage Updates**: Update stage, fuel, altitude, and speed every second
3. **Fast Forward**: Jump the simulation forward by X seconds

---

## **Edge Cases Handled**

* Commands executed in wrong order (e.g., launch before checks)
* Invalid inputs or arguments
* Stage transition failures
* Fuel depletion / mission abort scenarios
* Large fast-forward values beyond mission end
* Telemetry observer failures

---

## **Folder Structure**

```
src/
 └─ main/java/com/rocket/simulator/
     ├─ app/          # Main class, lifecycle, command dispatcher
     ├─ commands/     # Command interface + concrete commands
     ├─ state/        # Rocket stages
     ├─ observer/     # Telemetry system
     ├─ singleton/    # MissionControl singleton
     ├─ model/        # Rocket and Stage data
     └─ util/         # Logger and Validator utilities
resources/
 └─ config.properties  # Optional config values (fuel, stages, etc.)
logs/                  # Log files
```

---

## **How to Run**

1. Open the project in **IntelliJ IDEA**
2. Run the `RocketSimulator` main class
3. Type commands in the console:

   ```
   start_checks
   launch
   fast_forward 60
   exit
   ```
4. View telemetry and logs for rocket status updates

---

## **Design Patterns Used**

* **Command Pattern** → Encapsulates each user command
* **State Pattern** → Manages rocket stage behavior
* **Observer Pattern** → Telemetry updates
* **Singleton Pattern** → Centralized MissionControl instance

---