package com.simulator;

import com.simulator.commands.CommandDispatcher;
import com.simulator.core.MissionControl;
import com.simulator.utils.Logger;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Logger.info("========================================");
        Logger.info("   ROCKET LAUNCH SIMULATOR v1.0");
        Logger.info("   Based on Falcon 9 Specifications");
        Logger.info("========================================");
        Logger.info("Available commands:");
        Logger.info("  - start_checks : Begin pre-launch checks");
        Logger.info("  - launch : Launch the rocket");
        Logger.info("  - fast_forward <seconds> : Jump forward in time");
        Logger.info("  - status : Check current status");
        Logger.info("  - trigger_failure <type> : Trigger a mission failure (types: fuel_burn, fuel_capacity, engine_off, guidance_error)");
        Logger.info("  - exit : Exit simulator");
        Logger.info("========================================");

        MissionControl missionControl = MissionControl.getInstance();
        CommandDispatcher dispatcher = new CommandDispatcher();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                Logger.info("Exiting simulator. Come back soon!");
                missionControl.shutdown();
                break;
            }
            dispatcher.execute(input);
        }
        scanner.close();
    }
}