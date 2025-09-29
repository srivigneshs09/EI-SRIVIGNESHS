package com.simulator.checklist;

public class ChecklistItem {
    private String name;
    private boolean checked;
    private boolean required;

    public ChecklistItem(String name, boolean required) {
        this.name = name;
        this.checked = false;
        this.required = required;
    }

    public String getName() { return name; }
    public boolean isChecked() { return checked; }
    public void setChecked(boolean checked) { this.checked = checked; }
    public boolean isRequired() { return required; }
}