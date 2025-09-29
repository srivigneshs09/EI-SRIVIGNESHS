package rocket.simulator.commands;

import rocket.simulator.singleton.MissionControl;

public class FastForwardCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: fast_forward <seconds>");
            return;
        }

        try {
            int seconds = Integer.parseInt(args[1]);
            if (seconds <= 0) {
                System.out.println("⏩ Please enter a positive number of seconds.");
                return;
            }
            MissionControl.getInstance().getState().onFastForward(seconds);
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid number format.");
        }
    }
}
