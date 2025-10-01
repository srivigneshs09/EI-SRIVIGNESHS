package com.simulator.physics.strategies;

import com.simulator.core.TelemetryData;
import com.simulator.physics.RocketConstants;

public class Stage1UpdateStrategy implements StageUpdateStrategy {

    @Override
    public void update(TelemetryData telemetry, int missionTime) {
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
}