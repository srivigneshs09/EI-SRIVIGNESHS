package commands;

import receiver.Light;
import java.util.logging.Logger;

public class LightOffCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LightOffCommand.class.getName());
    private final Light light;

    public LightOffCommand(Light light) {
        if (light == null) {
            throw new IllegalArgumentException("Light receiver cannot be null");
        }
        this.light = light;
    }

    @Override
    public void execute() {
        try {
            light.turnOff();
        } catch (Exception e) {
            LOGGER.severe("Failed to execute LightOffCommand: " + e.getMessage());
        }
    }

    @Override
    public void undo() {
        try {
            light.turnOn();
        } catch (Exception e) {
            LOGGER.severe("Failed to undo LightOffCommand: " + e.getMessage());
        }
    }
}
