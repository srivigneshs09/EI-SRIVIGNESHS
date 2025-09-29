package rocket.simulator.commands;

import rocket.simulator.singleton.MissionControl;
import rocket.simulator.state.RocketState;

public class LaunchCommand implements Command {

    @Override
    public void execute(String[] args) {
        MissionControl mc = MissionControl.getInstance();
        // trigger launch (state will validate whether allowed)
        mc.getState().onLaunch();

        // If state switched into a flight stage, run real-time ticks until orbit or failure
        while (true) {
            RocketState current = mc.getState();
            String name = current.getName();

            // Only run per-second ticks for Stage1 and Stage2
            if ("Stage1State".equals(name) || "Stage2State".equals(name)) {
                // do one-second tick
                current.onFastForward(1);
                try {
                    Thread.sleep(1000L); // simulate real time 1s per tick
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Simulation interrupted.");
                    return;
                }
            } else {
                // Not in a timed stage -> exit loop
                break;
            }
        }
    }
}
