package com.simulator.physics;

public class TsiolkovskyCalculator {

    public double calculateDeltaV(double isp, double initialMass, double finalMass) {
        // Δv = Isp * g0 * ln(m0/mf)
        double g0 = RocketConstants.GRAVITY;
        return isp * g0 * Math.log(initialMass / finalMass);
    }

    public double calculateFuelMassRequired(double deltaV, double isp,
                                            double dryMass, double payloadMass) {
        // Rearranged Tsiolkovsky equation to find fuel mass
        double g0 = RocketConstants.GRAVITY;
        double massRatio = Math.exp(deltaV / (isp * g0));
        double initialMass = (dryMass + payloadMass) * massRatio;
        return initialMass - dryMass - payloadMass;
    }

    public double calculateBurnTime(double fuelMass, double massFlowRate) {
        return fuelMass / massFlowRate;
    }

    public double calculateMassFlowRate(double thrust, double isp) {
        // ṁ = F / (Isp * g0)
        double g0 = RocketConstants.GRAVITY;
        return thrust / (isp * g0);
    }
}