package rocket.main;

import rocket.simulator.CommandDispatcher;
import rocket.simulator.RocketSimulator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RocketSimulator simulator = RocketSimulator.getInstance();
        CommandDispatcher dispatcher = new CommandDispatcher(simulator);
        Scanner scanner = simulator.getScanner();
        System.out.println("🚀 Welcome to Rocket Launch Simulator");

        simulator.getCurrentState().onEnter(); // Initialize first state

        while (!simulator.isMissionEnded()) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();
            dispatcher.dispatch(input);
        }
    }
}