package rocket.simulator.observer;

import rocket.simulator.model.Rocket;

public class ConsoleTelemetryObserver implements Observer {
    private final Rocket rocket;

    public ConsoleTelemetryObserver(Rocket rocket) {
        this.rocket = rocket;
    }

    @Override
    public void update() {
        // Convert fuel to tonnes for display
        double fuelTonnes = rocket.getFuel() / 1000.0;
        System.out.printf("[Telemetry] Alt: %.2f km | Speed: %.0f km/h | Fuel: %.1f t%n",
                rocket.getAltitude(), rocket.getSpeed(), fuelTonnes);
    }
}
