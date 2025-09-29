package com.simulator.states;

public class LaunchingState implements RocketState {
    @Override
    public boolean canStartChecks() { return false; }

    @Override
    public boolean canLaunch() { return false; }

    @Override
    public boolean canFastForward() { return true; }

    @Override
    public boolean canUpdate() { return true; }

    @Override
    public String getStageName() { return "Launching"; }
}