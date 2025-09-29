package com.simulator.utils;

public class Logger {
    public static void info(String message) {
        System.out.println(message);
    }

    public static void success(String message) {
        System.out.println(message);
    }

    public static void warning(String message) {
        System.out.println(message);
    }

    public static void error(String message) {
        System.out.println(message);
    }

    public static void debug(String message) {
        System.out.println("[DEBUG] " + message);
    }
}