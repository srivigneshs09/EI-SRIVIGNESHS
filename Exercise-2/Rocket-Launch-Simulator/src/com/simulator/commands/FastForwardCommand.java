package com.simulator.commands;

import com.simulator.core.MissionControl;
import com.simulator.utils.Logger;

public class FastForwardCommand implements Command {
    @Override
    public void execute(String[] args) {
        MissionControl control = MissionControl.getInstance();

        if (!control.getRocket().getState().canFastForward()) {
            Logger.warning("Cannot fast-forward in current state: " +
                    control.getRocket().getState().getStageName());
            return;
        }

        if (args.length < 1) {
            Logger.error("Usage: fast_forward <seconds>");
            return;
        }

        try {
            int seconds = Integer.parseInt(args[0]);
            if (seconds <= 0) {
                Logger.error("Seconds must be a positive number.");
                return;
            }

            control.fastForward(seconds);
        } catch (NumberFormatException e) {
            Logger.error("Invalid number format: " + args[0]);
        }
    }
}