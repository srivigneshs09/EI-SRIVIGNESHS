package rocket.state;

import rocket.simulator.RocketSimulator;

import java.util.logging.Logger;

public class LaunchedState implements RocketState {
    private static final Logger LOGGER = Logger.getLogger(LaunchedState.class.getName());
    private final RocketSimulator simulator;

    public LaunchedState(RocketSimulator simulator) {
        this.simulator = simulator;
    }

    @Override
    public void onEnter() {
        simulator.setStage(1);
        simulator.setMissionTime(0);
        simulator.setAltitude(0);
        simulator.setSpeed(0);
        System.out.println("🚀 Rocket launches at T+0 altitude 0km, speed 0 km/hr, fuel 100%");
        simulator.startSimulation();
    }

    @Override
    public void processInput(String input) {
        // Delegate to CommandDispatcher
        simulator.getCurrentState(); // Ensure state is referenced
    }
}