package com.simulator.utils;

import com.simulator.core.TelemetryData;

public class DisplayFormatter {

    public static String formatTelemetry(TelemetryData telemetry, int missionTime, String stage) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("T+%ds | ", missionTime));
        sb.append(String.format("Stage: %s | ", stage));
        sb.append(String.format("Altitude: %.2f km | ", telemetry.getAltitude()));
        sb.append(String.format("Speed: %.0f km/h | ", telemetry.getSpeed()));
        sb.append(String.format("Fuel: %.2f%%", telemetry.getFuel()));
        return sb.toString();
    }

    public static String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        if (hours > 0) {
            return String.format("%d hours %d minutes %d seconds", hours, minutes, secs);
        } else if (minutes > 0) {
            return String.format("%d minutes %d seconds", minutes, secs);
        } else {
            return String.format("%d seconds", secs);
        }
    }

    public static void printSeparator() {
        System.out.println("=" .repeat(60));
    }

    public static void printHeader(String title) {
        printSeparator();
        int padding = (60 - title.length()) / 2;
        System.out.println(" ".repeat(padding) + title);
        printSeparator();
    }
}