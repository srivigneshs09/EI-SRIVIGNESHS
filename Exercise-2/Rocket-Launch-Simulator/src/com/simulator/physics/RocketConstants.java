package com.simulator.physics;

public class RocketConstants {
    // Falcon 9 specifications
    // Stage 1
    public static final double STAGE1_BURN_TIME = 162;        // seconds
    public static final double STAGE1_PROPELLANT_MASS = 411000; // kg

    // Stage 2
    public static final double STAGE2_BURN_TIME = 397;         // seconds
    public static final double STAGE2_PROPELLANT_MASS = 107500; // kg
    public static final double STAGE2_INITIAL_MASS = 138054;   // kg

    // Payload
    public static final double PAYLOAD_MASS = 4354;           // kg (Dragon capsule)

    // Total initial mass
    public static final double TOTAL_INITIAL_MASS = 549054;   // kg
}