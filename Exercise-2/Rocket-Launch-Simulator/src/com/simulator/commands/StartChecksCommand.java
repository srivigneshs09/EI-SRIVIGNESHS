package com.simulator.commands;

import com.simulator.core.MissionControl;
import com.simulator.states.ReadyForLaunchState;
import com.simulator.utils.Logger;

import java.util.Scanner;

public class StartChecksCommand implements Command {
    @Override
    public void execute(String[] args) {
        MissionControl control = MissionControl.getInstance();
        if (!control.getRocket().getState().canStartChecks()) {
            Logger.warning("Cannot perform checks in current state: " +
                    control.getRocket().getState().getStageName());
            return;
        }

        Logger.info("Starting pre-launch checks...");
        Scanner scanner = new Scanner(System.in);
        boolean allChecksPassed = true;

        // Array of checklist items
        String[] checklistItems = {
                "Engine Systems",
                "Telemetry Systems",
                "Fuel Loading",
                "Guidance Systems",
                "Range Safety"
        };

        // Prompt user for each checklist item
        for (String item : checklistItems) {
            boolean validInput = false;
            boolean isChecked = false;

            while (!validInput) {
                Logger.info(item + " [checked/unchecked]: ");
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equalsIgnoreCase("checked")) {
                    validInput = true;
                    isChecked = true;
                    Logger.info(item + " [checked/unchecked]: checked ✓");
                    Logger.success(item + " - CHECKED");
                } else if (input.equals("unchecked")) {
                    validInput = true;
                    isChecked = false;
                    Logger.info(item + " [checked/unchecked]: unchecked ✗");
                    Logger.warning(item + " - NOT CHECKED");
                } else {
                    Logger.warning("Invalid input. Please enter 'checked' or 'unchecked'.");
                }
            }

            if (!isChecked) {
                allChecksPassed = false;
            }
        }

        // Check if all items are checked
        if (allChecksPassed) {
            Logger.success("✓ All systems are 'Go' for launch.");
            control.getRocket().setState(new ReadyForLaunchState());
        } else {
            Logger.warning("Pre-launch checks failed. Cannot proceed to launch.");
        }
    }
}