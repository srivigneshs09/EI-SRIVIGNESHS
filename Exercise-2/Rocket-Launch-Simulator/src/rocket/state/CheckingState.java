package rocket.state;

import rocket.simulator.RocketSimulator;

import java.util.Scanner;
import java.util.logging.Logger;

public class CheckingState implements RocketState {
    private static final Logger LOGGER = Logger.getLogger(CheckingState.class.getName());
    private final RocketSimulator simulator;

    public CheckingState(RocketSimulator simulator) {
        this.simulator = simulator;
    }

    @Override
    public void onEnter() {
        Scanner scanner = simulator.getScanner();
        boolean engineChecked = false, telemetryChecked = false, fuelLoaded = false;
        String[] prompts = {"Engine condition [checked/unchecked]: ",
                "Telemetry condition [checked/unchecked]: ",
                "Fuel loaded [checked/unchecked]: "};
        boolean[] results = new boolean[3];
        String[] names = {"Engine", "Telemetry", "Fuel"};

        for (int i = 0; i < 3; i++) {
            while (true) {
                System.out.print(prompts[i]);
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("checked") || input.equals("unchecked")) {
                    results[i] = input.equals("checked");
                    break;
                }
                System.out.println("❌ Invalid input. Please enter 'checked' or 'unchecked'.");
                LOGGER.warning("Invalid input for " + names[i] + ": " + input);
            }
        }

        engineChecked = results[0];
        telemetryChecked = results[1];
        fuelLoaded = results[2];

        if (engineChecked && telemetryChecked && fuelLoaded) {
            System.out.println("✅ All systems are 'Go' for launch.");
            simulator.changeState(new ReadyState(simulator));
        } else {
            System.out.print("❌ Not ready for launch. Issues: ");
            if (!engineChecked) System.out.print("Engine unchecked, ");
            if (!telemetryChecked) System.out.print("Telemetry unchecked, ");
            if (!fuelLoaded) System.out.print("Fuel not loaded, ");
            System.out.println();
            simulator.changeState(new PreLaunchState(simulator));
        }
    }

    @Override
    public void processInput(String input) {
        System.out.println("❌ No commands allowed during checks. Awaiting check completion.");
    }
}