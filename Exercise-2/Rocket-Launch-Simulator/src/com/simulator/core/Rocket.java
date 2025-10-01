package com.simulator.core;

import com.simulator.physics.PhysicsEngine;
import com.simulator.physics.RocketConstants;
import com.simulator.states.PreLaunchState;
import com.simulator.states.RocketState;

public class Rocket {
    private RocketState state;
    private TelemetryData telemetry;
    private PhysicsEngine physicsEngine;
    private int currentStage;

    public Rocket() {
        this.state = new PreLaunchState();
        this.telemetry = new TelemetryData();
        this.physicsEngine = new PhysicsEngine();
        this.currentStage = 0;
    }

    public void update(int missionTime) {
        if (state.canUpdate()) {
            physicsEngine.updateTelemetry(telemetry, missionTime, currentStage);
        }
    }

    public void initializeStage2() {
        currentStage = 2;
        telemetry.setMass(RocketConstants.STAGE2_INITIAL_MASS+ RocketConstants.PAYLOAD_MASS);
        telemetry.setAltitude(80.0);
        telemetry.setSpeed(8000.0);
    }

    public void setState(RocketState newState) {
        this.state = newState;
        if (newState.getStageName().equals("Stage-1")) {
            currentStage = 1;
        }
    }

    public RocketState getState() {
        return state;
    }

    public TelemetryData getTelemetry() {
        return telemetry;
    }

    public int getCurrentStage() {
        return currentStage;
    }
}