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
            double remainingFuelPercentage = ((RocketConstants.TOTAL_INITIAL_MASS - RocketConstants.STAGE1_PROPELLANT_MASS)
                    / RocketConstants.TOTAL_INITIAL_MASS) * 100;
            telemetry.setFuel(remainingFuelPercentage);
            telemetry.setAltitude(80.0);
            telemetry.setSpeed(8000.0);
            return;
        }

        double fuelBurnMultiplier = telemetry.getFuelBurnMultiplier();
        double stage1FuelConsumed = RocketConstants.STAGE1_PROPELLANT_MASS * (missionTime / RocketConstants.STAGE1_BURN_TIME) * fuelBurnMultiplier;

        double remainingFuel = RocketConstants.TOTAL_INITIAL_MASS - stage1FuelConsumed;
        telemetry.setFuel(Math.max(0, (remainingFuel / RocketConstants.TOTAL_INITIAL_MASS) * 100));

        double altitudeGain = telemetry.isEngineOn() ? (6.13 / 50 * missionTime) : telemetry.getAltitude();
        altitudeGain *= telemetry.getGuidanceErrorFactor();
        telemetry.setAltitude(altitudeGain);

        double speedGain = telemetry.isEngineOn() ? (882.0 / 50 * missionTime) : telemetry.getSpeed();
        telemetry.setSpeed(speedGain);

        telemetry.setMass(RocketConstants.TOTAL_INITIAL_MASS - stage1FuelConsumed);
    }

    private void updateStage2(TelemetryData telemetry, int missionTime) {
        int stage2Time = missionTime - (int) RocketConstants.STAGE1_BURN_TIME;
        if (stage2Time > RocketConstants.STAGE2_BURN_TIME) {

            double remainingFuelPercentage = ((RocketConstants.TOTAL_INITIAL_MASS
                    - RocketConstants.STAGE1_PROPELLANT_MASS
                    - RocketConstants.STAGE2_PROPELLANT_MASS)
                    / RocketConstants.TOTAL_INITIAL_MASS) * 100;
            telemetry.setFuel(remainingFuelPercentage);
            return;
        }

        double progress = stage2Time / RocketConstants.STAGE2_BURN_TIME;

        double fuelBurnMultiplier = telemetry.getFuelBurnMultiplier();
        double stage2FuelConsumed = RocketConstants.STAGE2_PROPELLANT_MASS * progress * fuelBurnMultiplier;
        double totalFuelConsumed = RocketConstants.STAGE1_PROPELLANT_MASS + stage2FuelConsumed;

        double remainingFuel = RocketConstants.TOTAL_INITIAL_MASS - totalFuelConsumed;
        telemetry.setFuel(Math.max(0, (remainingFuel / RocketConstants.TOTAL_INITIAL_MASS) * 100));

        double altitudeGain = telemetry.isEngineOn() ? (80 + (320 * progress)) : telemetry.getAltitude();
        altitudeGain *= telemetry.getGuidanceErrorFactor();
        telemetry.setAltitude(altitudeGain);

        double speedGain = telemetry.isEngineOn() ? (8000 + (19358 * progress)) : telemetry.getSpeed();
        telemetry.setSpeed(speedGain);

        telemetry.setMass(RocketConstants.STAGE2_INITIAL_MASS +
                RocketConstants.PAYLOAD_MASS -
                stage2FuelConsumed);
    }
}