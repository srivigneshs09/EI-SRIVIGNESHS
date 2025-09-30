package com.simulator.commands;

import com.simulator.core.MissionControl;
import com.simulator.utils.Logger;

public class TriggerFailureCommand implements Command {
    @Override
    public void execute(String[] args) {
        MissionControl control = MissionControl.getInstance();
        if (!control.isRunning()) {
            Logger.warning("Cannot trigger failure. Mission not started.");
            return;
        }

        if (args.length < 1 || args[0].trim().isEmpty()) {
            Logger.warning("Usage: trigger_failure <type> (e.g., fuel_burn, fuel_capacity, engine_off, guidance_error)");
            return;
        }

        String failureType = args[0].toLowerCase();
        switch (failureType) {
            case "fuel_burn":
                control.triggerFuelBurn();
                Logger.warning("Triggered failure: Increased fuel burn rate.");
                break;
            case "fuel_capacity":
                control.triggerFuelCapacityReduction();
                Logger.warning("Triggered failure: Reduced fuel capacity.");
                break;
            case "engine_off":
                control.triggerEngineOff();
                Logger.warning("Triggered failure: Engine shutdown.");
                break;
            case "guidance_error":
                control.triggerGuidanceError();
                Logger.warning("Triggered failure: Guidance system error.");
                break;
            default:
                Logger.warning("Unknown failure type: " + failureType);
                Logger.info("Valid types: fuel_burn, fuel_capacity, engine_off, guidance_error");
        }
    }
}