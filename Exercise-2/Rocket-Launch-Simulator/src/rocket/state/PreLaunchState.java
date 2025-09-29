package rocket.state;

import rocket.simulator.RocketSimulator;

import java.util.logging.Logger;

public class PreLaunchState implements RocketState {
    private static final Logger LOGGER = Logger.getLogger(PreLaunchState.class.getName());
    private final RocketSimulator simulator;

    public PreLaunchState(RocketSimulator simulator) {
        this.simulator = simulator;
    }

    @Override
    public void onEnter() {
        System.out.println("🚀 Rocket in Pre-Launch. Enter 'start_checks' to proceed.");
    }

    @Override
    public void processInput(String input) {
        // Delegate to CommandDispatcher
        simulator.getCurrentState(); // Ensure state is referenced
    }
}