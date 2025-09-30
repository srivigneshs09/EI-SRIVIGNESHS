# Rocket Launch Simulator

## Problem Statement
The **Rocket Launch Simulator** is a Java-based console application simulating a Falcon 9 rocket launch, from pre-launch checks to orbit or failure. Users interact via commands to perform checks, launch, advance time, check status, or trigger failures, with telemetry (altitude, speed, fuel, mass) updated dynamically using the Tsiolkovsky rocket equation. The simulator ensures accurate state transitions and handles edge cases and manual failure scenarios (e.g., fuel depletion, engine shutdown).

## Features
- **Interactive Commands**: `start_checks`, `launch`, `fast_forward <seconds>`, `status`, `trigger_failure <type>`, `exit`.
- **State Transitions**: Pre-Launch, Ready, Stage-1, Stage-2, In-Orbit, Failed.
- **Real-Time Physics**: Uses Tsiolkovsky equation for velocity and mass updates.
- **Failure Simulation**: Manual triggers for fuel burn, fuel capacity reduction, engine shutdown, and guidance errors.
- **Telemetry Display**: Altitude (km), speed (km/h), fuel (%), mass (kg) on demand.
- **Error Handling**: Manages invalid inputs and premature commands.

## Edge Cases Handled
- **Invalid Checklist Inputs**: Accepts only `checked`/`unchecked` during `start_checks`.
- **Incomplete Checks**: Blocks `launch` if any checklist item is `unchecked`.
- **Premature Commands**: Prevents `launch` or `fast_forward` in wrong states.
- **Invalid Commands**: Rejects unknown commands (e.g., `fast`).
- **Failure Triggers**: Rejects `trigger_failure` before mission start or with invalid types.
- **Failure Scenarios**: Fuel depletion, engine off, or guidance errors cause mission failure.

## Design Patterns
- **Singleton**: `MissionControl` ensures a single instance.
- **State**: `RocketState` manages rocket lifecycle (Pre-Launch to Failed).
- **Command**: `Command` interface handles user inputs (`StartChecksCommand`, `TriggerFailureCommand`, etc.).
- **Strategy**: `PhysicsEngine` encapsulates dynamic telemetry calculations.

---

## File Structure
The project is organized to reflect its modular design:
```
RocketLaunchSimulator/
├── src/main/java/com/simulator/
   ├── Main.java
   ├── core/
   │   ├── MissionControl.java
   │   ├── Rocket.java
   │   ├── TelemetryData.java
   │   ├── TimeManager.java
   ├── states/
   │   ├── RocketState.java
   │   ├── PreLaunchState.java
   │   ├── ReadyForLaunchState.java
   │   ├── Stage1State.java
   │   ├── Stage2State.java
   │   ├── InOrbitState.java
   │   ├── FailedState.java
   ├── commands/
   │   ├── Command.java
   │   ├── StartChecksCommand.java
   │   ├── LaunchCommand.java
   │   ├── FastForwardCommand.java
   │   ├── StatusCommand.java
   │   ├── TriggerFailureCommand.java
   │   ├── CommandDispatcher.java
   ├── physics/
   │   ├── PhysicsEngine.java
   │   ├── RocketConstants.java
   ├── utils/
       ├── Logger.java
   
```

---

## Telemetry Values and References
- **Stage 1 (T+0s to T+162s)**:
    - T+50s: Altitude: 6.13 km, Speed: 882 km/h, Fuel: 69.14%.
    - T+162s: Altitude: 80 km, Speed: 8000 km/h, Fuel: 0%.
- **Stage 2 (T+162s to T+559s)**:
    - T+559s: Altitude: 400 km, Speed: 27358 km/h, Fuel: 49.62%.
- **Constants** (in `RocketConstants.java`):
    - `TOTAL_INITIAL_MASS`: 549,054 kg, `STAGE1_PROPELLANT_MASS`: 417,500 kg.
    - `STAGE2_INITIAL_MASS`: 111,500 kg, `PAYLOAD_MASS`: 22,800 kg.
    - `STAGE1_THRUST`: 7,607 kN, `STAGE2_THRUST`: 934 kN.
    - `STAGE1_ISP`: 282 s, `STAGE2_ISP`: 311 s.
- **References**:
    - [SpaceX Falcon 9](https://www.spacex.com/vehicles/falcon-9/).
    - [NASA Orbital Mechanics](https://www.nasa.gov/).

---

## Class Diagram 

![Class Diagram.png](OutputScreenShot/Class%20Diagram.png)

---

## Output Screenshots

Coming Soon...

