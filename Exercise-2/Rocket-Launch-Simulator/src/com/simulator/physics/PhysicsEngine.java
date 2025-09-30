package com.simulator.physics;

import com.simulator.core.TelemetryData;

public class PhysicsEngine {

    public PhysicsEngine() {
    }

    public void updateTelemetry(TelemetryData telemetry, int missionTime, int stage) {
        if (stage == 0) return;

        if (stage == 1) {
            updateStage1(telemetry, missionTime);
        } else if (stage == 2) {
            updateStage2(telemetry, missionTime);
        }
    }

    private void updateStage1(TelemetryData telemetry, int missionTime) {
        if (missionTime > RocketConstants.STAGE1_BURN_TIME) {
            telemetry.setFuel(0);
            telemetry.setAltitude(80.0);
            telemetry.setSpeed(8000.0);
            return;
        }

        double fuelBurnMultiplier = telemetry.getFuelBurnMultiplier();
        telemetry.setFuel(Math.max(0, 100 - (100.0 / 162 * missionTime * fuelBurnMultiplier)));

        double altitudeGain = telemetry.isEngineOn() ? (6.13 / 50 * missionTime) : telemetry.getAltitude();
        altitudeGain *= telemetry.getGuidanceErrorFactor();
        telemetry.setAltitude(altitudeGain);

        double speedGain = telemetry.isEngineOn() ? (882.0 / 50 * missionTime) : telemetry.getSpeed();
        telemetry.setSpeed(speedGain);
        telemetry.setMass(RocketConstants.TOTAL_INITIAL_MASS -
                (RocketConstants.STAGE1_PROPELLANT_MASS * (missionTime / 162.0)));
    }

    private void updateStage2(TelemetryData telemetry, int missionTime) {
        int stage2Time = missionTime - (int) RocketConstants.STAGE1_BURN_TIME;
        if (stage2Time > RocketConstants.STAGE2_BURN_TIME) {
            telemetry.setFuel(0);
            return;
        }

        double progress = stage2Time / RocketConstants.STAGE2_BURN_TIME;

        double fuelBurnMultiplier = telemetry.getFuelBurnMultiplier();
        telemetry.setFuel(Math.max(0, 100 - (50.38 * progress * fuelBurnMultiplier)));

        double altitudeGain = telemetry.isEngineOn() ? (80 + (320 * progress)) : telemetry.getAltitude();
        altitudeGain *= telemetry.getGuidanceErrorFactor();
        telemetry.setAltitude(altitudeGain);

        double speedGain = telemetry.isEngineOn() ? (8000 + (19358 * progress)) : telemetry.getSpeed();
        telemetry.setSpeed(speedGain);
        telemetry.setMass(RocketConstants.STAGE2_INITIAL_MASS +
                RocketConstants.PAYLOAD_MASS -
                (RocketConstants.STAGE2_PROPELLANT_MASS * progress));
    }
}