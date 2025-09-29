package rocket.simulator.model;

import rocket.simulator.observer.Observer;
import rocket.simulator.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class Rocket implements Subject {
    private double altitude = 0; // km
    private double speed = 0;    // km/h
    private double fuel = 519000;   // kg (use kg internally)

    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer o) { observers.add(o); }

    @Override
    public void removeObserver(Observer o) { observers.remove(o); }

    @Override
    public void notifyObservers() {
        for (Observer o : new ArrayList<>(observers)) {
            try {
                o.update();
            } catch (Exception ex) {
                // Protect subject from observer failures
                System.err.println("Observer update failed: " + ex.getMessage());
            }
        }
    }

    // Note: altitude in km, speed in km/h, fuel in kg
    public double getAltitude() { return altitude; }
    public void setAltitude(double altitude) {
        this.altitude = altitude;
        notifyObservers();
    }

    public double getSpeed() { return speed; }
    public void setSpeed(double speed) {
        this.speed = speed;
        notifyObservers();
    }

    public double getFuel() { return fuel; }
    /**
     * Consume fuel in kg
     */
    public void consumeFuel(double amountKg) {
        this.fuel -= amountKg;
        if (fuel < 0) fuel = 0;
        notifyObservers();
    }
}
