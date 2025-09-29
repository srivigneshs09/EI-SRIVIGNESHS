package com.simulator.exceptions;

public class PhysicsCalculationException extends SimulatorException {
    public PhysicsCalculationException(String message) {
        super(message);
    }

    public PhysicsCalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}