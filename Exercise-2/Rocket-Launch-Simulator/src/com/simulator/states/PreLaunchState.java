package com.simulator.states;

public class PreLaunchState implements RocketState {
    @Override
    public boolean canStartChecks() { return true; }

    @Override
    public boolean canLaunch() { return false; }

    @Override
    public boolean canFastForward() { return false; }

    @Override
    public boolean canUpdate() { return false; }

    @Override
    public String getStageName() { return "Pre-Launch"; }
}