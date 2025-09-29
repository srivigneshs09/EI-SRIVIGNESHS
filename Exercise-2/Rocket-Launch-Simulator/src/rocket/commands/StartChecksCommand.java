package rocket.commands;

import rocket.simulator.RocketSimulator;
import rocket.state.CheckingState;
import rocket.state.PreLaunchState;

public class StartChecksCommand implements Command {
    private final RocketSimulator simulator;

    public StartChecksCommand(RocketSimulator simulator) {
        this.simulator = simulator;
    }

    @Override
    public void execute(String[] params) throws Exception {
        if (!(simulator.getCurrentState() instanceof PreLaunchState)) {
            throw new IllegalStateException("start_checks only allowed in Pre-Launch state");
        }
        if (params.length != 1) {
            throw new IllegalArgumentException("start_checks takes no parameters");
        }
        simulator.changeState(new CheckingState(simulator));
    }
}