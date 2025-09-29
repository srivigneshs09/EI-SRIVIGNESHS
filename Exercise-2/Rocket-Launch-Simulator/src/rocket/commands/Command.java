package rocket.commands;

public interface Command {
    void execute(String[] params) throws Exception;
}