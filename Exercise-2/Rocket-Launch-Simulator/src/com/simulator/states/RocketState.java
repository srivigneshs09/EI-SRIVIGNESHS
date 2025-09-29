package com.simulator.states;

public interface RocketState {
    boolean canStartChecks();
    boolean canLaunch();
    boolean canFastForward();
    boolean canUpdate();
    String getStageName();
}