package com.simulator.physics.strategies;

import com.simulator.core.TelemetryData;
import com.simulator.physics.RocketConstants;

public class Stage2UpdateStrategy implements StageUpdateStrategy {

    @Override
    public void update(TelemetryData telemetry, int missionTime) {
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