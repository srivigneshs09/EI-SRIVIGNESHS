# Rocket Launch Simulator

## Overview
The **Rocket Launch Simulator** is a Java-based console application that simulates the launch of a Falcon 9 rocket, from pre-launch checks to achieving orbit. The simulator models the rocket's flight stages, tracks telemetry data (altitude, speed, fuel), and enforces a state-driven workflow. Users interact via commands (`start_checks`, `launch`, `fast_forward`, `status`, `exit`), with telemetry updates displayed only on demand, ensuring a realistic and interactive experience.

The project adheres to object-oriented design principles and uses design patterns to ensure modularity, maintainability, and extensibility. It simulates a simplified Falcon 9 launch, with telemetry values tuned to match specific milestones (e.g., 6.13 km altitude at T+50s, orbit at 400 km).

---

## Features
- **Interactive Commands**: Users can:
    - `start_checks`: Input `checked` or `unchecked` for pre-launch checklist items (Engine Systems, Telemetry Systems, Fuel Loading, Guidance Systems, Range Safety). All must be `checked` to proceed to launch.
    - `launch`: Initiates the mission, transitioning to Stage 1.
    - `fast_forward <seconds>`: Advances simulation time and displays telemetry.
    - `status`: Shows current telemetry and state.
    - `exit`: Terminates the simulator.
- **State Transitions**: Models rocket stages (Pre-Launch, Ready, Stage-1, Stage-2, In-Orbit, Failed).
- **Background Execution**: Updates telemetry silently every second, displaying only on command.
- **Telemetry Data**: Tracks altitude (km), speed (km/h), fuel (%), and mass (kg).
- **Error Handling**: Prevents invalid actions (e.g., launching without checks, fast-forwarding post-orbit).

---

## Edge Cases Handled
The simulator robustly handles the following edge cases:
- **Invalid Checklist Inputs**: During `start_checks`, only `checked` or `unchecked` inputs are accepted. Invalid inputs (e.g., `check`, `uncheck`) prompt an error message and re-request input.
- **Incomplete Pre-Launch Checks**: If any checklist item is `unchecked`, the rocket remains in `PreLaunchState`, and `launch` fails with “Cannot launch in current state: Pre-Launch. Please complete pre-launch checks first.”
- **Premature Launch**: Attempting `launch` in `PreLaunchState`, `InOrbitState`, or `FailedState` produces an error and prevents mission start.
- **Invalid Commands**: Unknown commands (e.g., `fast`) result in “Unknown command: <input>.”
- **Fast-Forward Before Mission Start**: `fast_forward` before `launch` fails with “Cannot fast-forward. Mission not started.”
- **Fast-Forward Post-Orbit**: `fast_forward` in `InOrbitState` fails with “Cannot fast-forward. Mission already complete.” and displays current telemetry.
- **Fuel Depletion Failure**: If fuel reaches 0% before orbit (outside `InOrbitState`), the rocket transitions to `FailedState` with “Mission Failed due to insufficient fuel.”
- **Concurrent Time Updates**: Synchronization prevents background timer (`updateSimulation`) from interfering with `fast_forward` or `status`, ensuring accurate time increments.

---

### Design Patterns
The project employs the following design patterns to ensure a robust architecture:
- **Singleton Pattern**: `MissionControl` ensures a single instance manages the simulation, centralizing control and state.
- **State Pattern**: `RocketState` interface and concrete states (`PreLaunchState`, `ReadyForLaunchState`, `Stage1State`, `Stage2State`, `InOrbitState`, `FailedState`) manage the rocket’s lifecycle and enforce valid actions.
- **Command Pattern**: `Command` interface and concrete commands (`StartChecksCommand`, `LaunchCommand`, `FastForwardCommand`, `StatusCommand`) handle user inputs via `CommandDispatcher`, promoting loose coupling.
- **Strategy Pattern**: `PhysicsEngine` encapsulates stage-specific telemetry calculations, allowing easy updates or replacements.
- **Observer Pattern** (implicit): Telemetry updates are triggered by commands, notifying the console only when needed.

---

### Why These Patterns?
- **Singleton**: Ensures one simulation instance, avoiding conflicts in state or timing.
- **State**: Simplifies complex state transitions and enforces constraints (e.g., no launch without checks).
- **Command**: Decouples user input from execution logic, making it easy to add new commands.
- **Strategy**: Isolates physics calculations, enabling adjustments without affecting core logic.
- **Observer**: Reduces unnecessary output by updating telemetry only on user request.

---

## File Structure
The project is organized to reflect its modular design:
```
Rocket-Launch-Simulator/
├── src/com/simulator/
   ├── Main.java                # Entry point
   ├── core/
   │   ├── MissionControl.java  # Singleton managing simulation lifecycle
   │   ├── Rocket.java         # Manages state and telemetry
   │   ├── TelemetryData.java  # Stores altitude, speed, fuel, mass
   │   ├── TimeManager.java    # Tracks mission elapsed time
   ├── states/
   │   ├── RocketState.java    # Interface for state pattern
   │   ├── PreLaunchState.java
   │   ├── ReadyForLaunchState.java
   │   ├── Stage1State.java
   │   ├── Stage2State.java
   │   ├── InOrbitState.java
   │   ├── FailedState.java
   ├── commands/
   │   ├── Command.java        # Interface for command pattern
   │   ├── StartChecksCommand.java
   │   ├── LaunchCommand.java
   │   ├── FastForwardCommand.java
   │   ├── StatusCommand.java
   │   ├── CommandDispatcher.java
   ├── physics/
   │   ├── PhysicsEngine.java  # Strategy for telemetry calculations
   │   ├── TsiolkovskyCalculator.java
   │   ├── RocketConstants.java
   ├── utils/
       ├── Logger.java         # Handles console output
       ├── DisplayFormatter.java
```

---

## Telemetry Values and References
The telemetry values are tuned to match the specified output, derived from simplified Falcon 9 performance data:
- **Stage 1 (T+0s to T+162s)**:
    - T+50s: Altitude: 6.13 km, Speed: 882 km/h, Fuel: 69.14%
    - T+162s: Altitude: 80 km, Speed: 8000 km/h, Fuel: 0%
    - **Reference**: Approximated from Falcon 9’s first-stage burn time (~162s) and Karman line (~80 km) [SpaceX Falcon 9 Overview](https://www.spacex.com/vehicles/falcon-9/).
- **Stage 2 (T+162s to T+559s)**:
    - T+362s: Altitude: 280.65 km, Speed: 20237 km/h, Fuel: 49.62%
    - T+559s: Altitude: 400 km, Speed: 27358 km/h, Fuel: 49.62%
    - **Reference**: Based on low Earth orbit altitude (~400 km) and orbital velocity (~7.6 km/s or 27360 km/h) [NASA Orbital Mechanics](https://www.nasa.gov/).
- **Constants** (in `RocketConstants.java`):
    - `STAGE1_BURN_TIME`: 162s
    - `STAGE2_BURN_TIME`: 397s
    - `TOTAL_INITIAL_MASS`: 549054 kg
    - `STAGE1_PROPELLANT_MASS`: 417500 kg
    - `STAGE2_INITIAL_MASS`: 111500 kg
    - `PAYLOAD_MASS`: 22800 kg
    - **Reference**: Sourced from SpaceX Falcon 9 specifications [SpaceX Falcon 9](https://www.spacex.com/vehicles/falcon-9/).

The `PhysicsEngine` uses linear interpolation for simplicity, ensuring exact matches to the specified telemetry values.

---

## Class Diagram 

![Class Diagram.png](OutputScreenShot/Class%20Diagram.png)

---

## Output Screenshots

Coming Soon...

---

## References
- [SpaceX Launch Video](https://youtu.be/9H8puVq2oi0?si=-aiHeAYEBpwCzHyG )
