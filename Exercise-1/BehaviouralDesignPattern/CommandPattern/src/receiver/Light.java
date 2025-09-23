package receiver;

import java.util.logging.Logger;

public class Light {
    private static final Logger LOGGER = Logger.getLogger(Light.class.getName());
    private boolean isOn = false;

    public void turnOn() {
        if (isOn) {
            LOGGER.warning("Light is already ON.");
            return;
        }
        isOn = true;
        LOGGER.info("Light is ON.");
    }

    public void turnOff() {
        if (!isOn) {
            LOGGER.warning("Light is already OFF.");
            return;
        }
        isOn = false;
        LOGGER.info("Light is OFF.");
    }

    public boolean isOn() {
        return isOn;
    }
}
