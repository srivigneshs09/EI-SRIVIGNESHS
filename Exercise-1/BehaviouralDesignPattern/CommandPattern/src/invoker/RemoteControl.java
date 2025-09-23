package invoker;

import commands.Command;
import java.util.Stack;
import java.util.logging.Logger;

public class RemoteControl {
    private static final Logger LOGGER = Logger.getLogger(RemoteControl.class.getName());
    private final Stack<Command> commandHistory = new Stack<>();

    public void executeCommand(Command command) {
        if (command == null) {
            LOGGER.warning("Command cannot be null.");
            return;
        }
        command.execute();
        commandHistory.push(command);
    }

    public void undoLastCommand() {
        if (commandHistory.isEmpty()) {
            LOGGER.info("No commands to undo.");
            return;
        }
        Command lastCommand = commandHistory.pop();
        lastCommand.undo();
    }
}
