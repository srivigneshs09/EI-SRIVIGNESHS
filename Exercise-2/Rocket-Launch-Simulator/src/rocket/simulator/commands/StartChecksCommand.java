package rocket.simulator.commands;

import rocket.simulator.singleton.MissionControl;

public class StartChecksCommand implements Command {
    @Override
    public void execute(String[] args) {
        MissionControl.getInstance().getState().onStartChecks();
    }
}
