package com.simulator.commands;

import com.simulator.utils.Logger;
import java.util.HashMap;
import java.util.Map;

public class CommandDispatcher {
    private final Map<String, Command> commandMap;

    public CommandDispatcher() {
        commandMap = new HashMap<>();
        commandMap.put("start_checks", new StartChecksCommand());
        commandMap.put("launch", new LaunchCommand());
        commandMap.put("fast_forward", new FastForwardCommand());
        commandMap.put("status", new StatusCommand());
        commandMap.put("trigger_failure", new TriggerFailureCommand());
    }

    public void execute(String input) {
        if (input == null || input.isEmpty()) {
            return;
        }

        String[] parts = input.split("\\s+");
        String commandName = parts[0].toLowerCase();

        Command command = commandMap.get(commandName);
        if (command != null) {
            String[] args = new String[parts.length - 1];
            System.arraycopy(parts, 1, args, 0, parts.length - 1);
            try {
                command.execute(args);
            } catch (Exception e) {
                Logger.error("Error executing command: " + e.getMessage());
            }
        } else {
            Logger.warning("Unknown command: " + commandName);
        }
    }
}