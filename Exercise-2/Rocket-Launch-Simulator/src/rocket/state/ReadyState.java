package rocket.state;

import rocket.simulator.RocketSimulator;

import java.util.logging.Logger;

public class ReadyState implements RocketState {
    private static final Logger LOGGER = Logger.getLogger(ReadyState.class.getName());
    private final RocketSimulator simulator;

    public ReadyState(RocketSimulator simulator) {
        this.simulator = simulator;
    }

    @Override
    public void onEnter() {
        System.out.println("✅ Rocket is ready for launch. Enter 'launch' to begin.");
    }

    @Override
    public void processInput(String input) {
        // Delegate to CommandDispatcher
        simulator.getCurrentState(); // Ensure state is referenced
    }
}