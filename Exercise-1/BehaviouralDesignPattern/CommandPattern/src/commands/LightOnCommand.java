package commands;

import receiver.Light;
import java.util.logging.Logger;

public class LightOnCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LightOnCommand.class.getName());
    private final Light light;

    public LightOnCommand(Light light) {
        if (light == null) {
            throw new IllegalArgumentException("Light receiver cannot be null");
        }
        this.light = light;
    }

    @Override
    public void execute() {
        try {
            light.turnOn();
        } catch (Exception e) {
            LOGGER.severe("Failed to execute LightOnCommand: " + e.getMessage());
        }
    }

    @Override
    public void undo() {
        try {
            light.turnOff();
        } catch (Exception e) {
            LOGGER.severe("Failed to undo LightOnCommand: " + e.getMessage());
        }
    }
}
