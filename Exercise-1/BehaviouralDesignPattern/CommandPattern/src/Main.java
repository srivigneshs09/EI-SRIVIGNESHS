import commands.*;
import receiver.Light;
import invoker.RemoteControl;
import utils.InputUtils;

import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        RemoteControl remoteControl = new RemoteControl();

        boolean keepRunning = true;

        while (keepRunning) {
            String input = InputUtils.getUserInput(
                    "\nChoose option: \n1. Turn Light ON\n2. Turn Light OFF\n3. Undo Last Command\n4. Exit\n"
            );

            switch (input) {
                case "1" -> remoteControl.executeCommand(lightOn);
                case "2" -> remoteControl.executeCommand(lightOff);
                case "3" -> remoteControl.undoLastCommand();
                case "4" -> {
                    keepRunning = false;
                    LOGGER.info("Exiting application. Goodbye!");
                }
                default -> LOGGER.warning("Invalid choice. Please try again.");
            }
        }
    }
}
