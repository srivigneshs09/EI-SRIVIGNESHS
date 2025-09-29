package com.simulator.states;

public class ReadyForLaunchState implements RocketState {
    @Override
    public boolean canStartChecks() { return false; }

    @Override
    public boolean canLaunch() { return true; }

    @Override
    public boolean canFastForward() { return false; }

    @Override
    public boolean canUpdate() { return false; }

    @Override
    public String getStageName() { return "Ready"; }
}