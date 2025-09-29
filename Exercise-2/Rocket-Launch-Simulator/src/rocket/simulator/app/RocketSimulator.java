package rocket.simulator.app;

import java.util.Scanner;

public class RocketSimulator {
    public static void main(String[] args) {
        CommandDispatcher dispatcher = new CommandDispatcher();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Rocket Launch Simulator");
        System.out.println("Available commands: start_checks, launch, fast_forward <sec>, exit");

        boolean running = true;
        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Simulation terminated.");
                running = false;
            } else {
                dispatcher.dispatch(input);
            }
        }

        scanner.close();
    }
}
