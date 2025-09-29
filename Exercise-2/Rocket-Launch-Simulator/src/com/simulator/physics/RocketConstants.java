package com.simulator.physics;

public class RocketConstants {
    // Falcon 9 specifications
    // Stage 1
    public static final double STAGE1_THRUST = 7607000;        // N (sea level)
    public static final double STAGE1_ISP_SEA_LEVEL = 282;    // seconds
    public static final double STAGE1_ISP_VACUUM = 311;        // seconds
    public static final double STAGE1_BURN_TIME = 162;        // seconds
    public static final double STAGE1_PROPELLANT_MASS = 411000; // kg
    public static final double STAGE1_DRY_MASS = 22200;       // kg
    public static final double STAGE1_INITIAL_MASS = 433200;   // kg

    // Stage 2
    public static final double STAGE2_THRUST = 934000;         // N (vacuum)
    public static final double STAGE2_ISP_VACUUM = 348;        // seconds
    public static final double STAGE2_BURN_TIME = 397;         // seconds
    public static final double STAGE2_PROPELLANT_MASS = 107500; // kg
    public static final double STAGE2_DRY_MASS = 4000;         // kg
    public static final double STAGE2_INITIAL_MASS = 111500;   // kg

    // Payload
    public static final double PAYLOAD_MASS = 4354;           // kg (Dragon capsule)

    // Total initial mass
    public static final double TOTAL_INITIAL_MASS = 549054;   // kg

    // Physics constants
    public static final double GRAVITY = 9.81;                 // m/s²
    public static final double DRAG_COEFFICIENT = 0.3;
    public static final double CROSS_SECTIONAL_AREA = 10.52;   // m²

    // Target orbit
    public static final double TARGET_ALTITUDE = 400;          // km (ISS orbit)
    public static final double ORBITAL_VELOCITY = 27358;       // km/h
}