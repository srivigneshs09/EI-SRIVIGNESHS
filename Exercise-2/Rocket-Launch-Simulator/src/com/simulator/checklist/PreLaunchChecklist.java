package com.simulator.checklist;

import com.simulator.utils.Logger;
import java.util.*;

public class PreLaunchChecklist {
    private List<ChecklistItem> checklistItems;
    private Scanner scanner;

    public PreLaunchChecklist() {
        this.scanner = new Scanner(System.in);
        initializeChecklist();
    }

    private void initializeChecklist() {
        checklistItems = new ArrayList<>();
        checklistItems.add(new ChecklistItem("Engine Systems", true));
        checklistItems.add(new ChecklistItem("Telemetry Systems", true));
        checklistItems.add(new ChecklistItem("Fuel Loading", true));
        checklistItems.add(new ChecklistItem("Guidance Systems", true));
        checklistItems.add(new ChecklistItem("Range Safety", true));
    }

    public boolean performChecks() {
        Logger.info("Starting pre-launch checks...\n");

        for (ChecklistItem item : checklistItems) {
            boolean validInput = false;
            while (!validInput) {
                System.out.printf("%s [checked/unchecked]: ", item.getName());
                String input = scanner.nextLine().trim().toLowerCase();

                if (input.equals("checked")) {
                    item.setChecked(true);
                    Logger.success("✓ " + item.getName() + " - CHECKED");
                    validInput = true;
                } else if (input.equals("unchecked")) {
                    item.setChecked(false);
                    Logger.warning("✗ " + item.getName() + " - UNCHECKED");
                    validInput = true;
                } else {
                    Logger.error("Invalid input. Please type 'checked' or 'unchecked'");
                }
            }
        }

        return validateChecklist();
    }

    private boolean validateChecklist() {
        List<String> failedItems = new ArrayList<>();

        for (ChecklistItem item : checklistItems) {
            if (item.isRequired() && !item.isChecked()) {
                failedItems.add(item.getName());
            }
        }

        if (!failedItems.isEmpty()) {
            Logger.error("\nPre-launch checks failed. The following systems are not ready:");
            for (String item : failedItems) {
                Logger.error("  - " + item);
            }
            return false;
        }

        return true;
    }
}