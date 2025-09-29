package rocket.commands;

import rocket.simulator.RocketSimulator;
import rocket.state.LaunchedState;

public class FastForwardCommand implements Command {
    private final RocketSimulator simulator;

    public FastForwardCommand(RocketSimulator simulator) {
        this.simulator = simulator;
    }

    @Override
    public void execute(String[] params) throws Exception {
        if (!(simulator.getCurrentState() instanceof LaunchedState)) {
            throw new IllegalStateException("fast_forward only allowed in Launched state");
        }
        if (params.length != 2) {
            throw new IllegalArgumentException("fast_forward requires one parameter: seconds");
        }
        try {
            long seconds = Long.parseLong(params[1]);
            simulator.fastForward(seconds);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number of seconds: " + params[1]);
        }
    }
}