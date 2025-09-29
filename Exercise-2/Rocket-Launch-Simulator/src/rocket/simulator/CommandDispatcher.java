package rocket.simulator;

import rocket.commands.*;
import java.util.HashMap;
import java.util.Map;

public class CommandDispatcher {
    private final Map<String, Command> commands = new HashMap<>();
    private final RocketSimulator simulator;

    public CommandDispatcher(RocketSimulator simulator) {
        this.simulator = simulator;
        commands.put("start_checks", new StartChecksCommand(simulator));
        commands.put("launch", new LaunchCommand(simulator));
        commands.put("fast_forward", new FastForwardCommand(simulator));
        commands.put("exit", new ExitCommand(simulator));
    }

    public void dispatch(String input) {
        if (input.isEmpty()) return;
        String[] parts = input.split("\\s+");
        String commandName = parts[0].toLowerCase();
        Command command = commands.get(commandName);
        if (command != null) {
            try {
                command.execute(parts);
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Unknown command. Try: start_checks, launch, fast_forward X, exit");
        }
    }
}