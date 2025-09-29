package rocket.simulator.singleton;

import rocket.simulator.model.Rocket;
import rocket.simulator.observer.ConsoleTelemetryObserver;
import rocket.simulator.state.IdleState;
import rocket.simulator.state.RocketState;

public class MissionControl {
    private static MissionControl instance;
    private final Rocket rocket;
    private RocketState state;

    private MissionControl() {
        this.rocket = new Rocket();
        // Start in IdleState to require start_checks before launch
        this.state = new IdleState();

        // Attach observer for telemetry (console)
        rocket.addObserver(new ConsoleTelemetryObserver(rocket));
    }

    public static synchronized MissionControl getInstance() {
        if (instance == null) {
            instance = new MissionControl();
        }
        return instance;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public RocketState getState() {
        return state;
    }

    public void setState(RocketState newState) {
        this.state = newState;
    }
}
