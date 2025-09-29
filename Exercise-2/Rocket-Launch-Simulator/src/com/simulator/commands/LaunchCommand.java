package com.simulator.commands;

import com.simulator.core.MissionControl;
import com.simulator.utils.Logger;

public class LaunchCommand implements Command {
    @Override
    public void execute(String[] args) {
        MissionControl control = MissionControl.getInstance();
        if (!control.getRocket().getState().canLaunch()) {
            Logger.warning("Cannot launch in current state: " +
                    control.getRocket().getState().getStageName());
            Logger.info("Please complete pre-launch checks first.");
            return;
        }
        control.startMission();
    }
}