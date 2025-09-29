package com.simulator.commands;

import com.simulator.core.MissionControl;
import com.simulator.utils.Logger;

public class StatusCommand implements Command {
    @Override
    public void execute(String[] args) {
        MissionControl control = MissionControl.getInstance();
        control.displayTelemetry();
        Logger.info("Current State: " + control.getRocket().getState().getStageName());
    }
}