package com.simulator.core;

import com.simulator.physics.RocketConstants;

public class TelemetryData {
    private double altitude; // in kilometers
    private double speed;    // in km/h
    private double fuel;     // in percentage
    private double mass;     // in kilograms
    private double acceleration; // in m/s^2
    private double fuelBurnMultiplier; // For fuel burn failure
    private boolean isEngineOn; // For engine failure
    private double guidanceErrorFactor; // For guidance failure

    public TelemetryData() {
        this.altitude = 0.0;
        this.speed = 0.0;
        this.fuel = 100.0;
        this.mass = RocketConstants.TOTAL_INITIAL_MASS;
        this.acceleration = 0.0;
        this.fuelBurnMultiplier = 1.0;
        this.isEngineOn = true;
        this.guidanceErrorFactor = 1.0;
    }

    public double getAltitude() { return altitude; }
    public void setAltitude(double altitude) { this.altitude = altitude; }
    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }
    public double getFuel() { return fuel; }
    public void setFuel(double fuel) { this.fuel = fuel; }
    public double getMass() { return mass; }
    public void setMass(double mass) { this.mass = mass; }
    public double getAcceleration() { return acceleration; }
    public void setAcceleration(double acceleration) { this.acceleration = acceleration; }
    public double getFuelBurnMultiplier() { return fuelBurnMultiplier; }
    public void setFuelBurnMultiplier(double multiplier) { this.fuelBurnMultiplier = multiplier; }
    public boolean isEngineOn() { return isEngineOn; }
    public void setEngineOn(boolean isEngineOn) { this.isEngineOn = isEngineOn; }
    public double getGuidanceErrorFactor() { return guidanceErrorFactor; }
    public void setGuidanceErrorFactor(double factor) { this.guidanceErrorFactor = factor; }
}