package rocket.commands;

import rocket.simulator.RocketSimulator;
import rocket.state.LaunchedState;
import rocket.state.ReadyState;

public class LaunchCommand implements Command {
    private final RocketSimulator simulator;

    public LaunchCommand(RocketSimulator simulator) {
        this.simulator = simulator;
    }

    @Override
    public void execute(String[] params) throws Exception {
        if (!(simulator.getCurrentState() instanceof ReadyState)) {
            throw new IllegalStateException("launch only allowed in Ready state");
        }
        if (params.length != 1) {
            throw new IllegalArgumentException("launch takes no parameters");
        }
        simulator.changeState(new LaunchedState(simulator));
    }
}