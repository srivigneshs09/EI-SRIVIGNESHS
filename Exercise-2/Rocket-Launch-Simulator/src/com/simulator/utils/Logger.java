package com.simulator.utils;

public class Logger {
    // ANSI escape codes for colors
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";

    public static void info(String message) {
        System.out.println(message);
    }

    public static void success(String message) {
        System.out.println(GREEN + message + RESET);
    }

    public static void warning(String message) {
        System.out.println(RED + message + RESET);
    }

    public static void error(String message) {
        System.out.println(RED + message + RESET);
    }

    public static void debug(String message) {
        System.out.println(RED + "[DEBUG] " + message + RESET);
    }
}
