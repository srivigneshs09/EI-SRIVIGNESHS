package rocket.simulator.app;

import rocket.simulator.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandDispatcher {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandDispatcher() {
        commands.put("start_checks", new StartChecksCommand());
        commands.put("launch", new LaunchCommand());
        commands.put("fast_forward", new FastForwardCommand());
    }

    public void dispatch(String input) {
        if (input == null || input.isBlank()) return;
        String[] parts = input.trim().split("\\s+");
        if (parts.length == 0) return;

        Command command = commands.get(parts[0].toLowerCase());
        if (command != null) {
            command.execute(parts);
        } else {
            System.out.println("Unknown command. Try: start_checks, launch, fast_forward <sec>, exit");
        }
    }
}
