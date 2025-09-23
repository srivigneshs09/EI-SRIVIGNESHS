package utils;

import java.util.Scanner;
import java.util.logging.Logger;

public class InputUtils {
    private static final Logger LOGGER = Logger.getLogger(InputUtils.class.getName());
    private static final Scanner scanner = new Scanner(System.in);

    public static String getUserInput(String prompt) {
        LOGGER.info(prompt);
        String input = "";
        try {
            input = scanner.nextLine().trim();
        } catch (Exception e) {
            LOGGER.severe("Error reading input: " + e.getMessage());
        }
        return input;
    }
}
