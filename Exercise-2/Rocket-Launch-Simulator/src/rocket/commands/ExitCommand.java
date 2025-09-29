package rocket.commands;

import rocket.simulator.RocketSimulator;

public class ExitCommand implements Command {
    private final RocketSimulator simulator;

    public ExitCommand(RocketSimulator simulator) {
        this.simulator = simulator;
    }

    @Override
    public void execute(String[] params) throws Exception {
        if (params.length != 1) {
            throw new IllegalArgumentException("exit takes no parameters");
        }
        simulator.exit();
    }
}