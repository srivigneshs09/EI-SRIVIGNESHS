package com.simulator.core;

public class TelemetryData {
    private double altitude;     // km
    private double speed;        // km/h
    private double fuel;         // percentage
    private double mass;         // kg
    private double acceleration; // m/s²

    public TelemetryData() {
        this.altitude = 0;
        this.speed = 0;
        this.fuel = 100;
        this.mass = 549054; // Falcon 9 initial mass
        this.acceleration = 0;
    }

    // Getters and Setters
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
}