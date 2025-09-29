package rocket.model;

public class RocketPhysics {
    // Falcon 9-like parameters
    public static final double INITIAL_MASS = 549_000; // kg (total: payload + dry + prop)
    public static final double PROP_MASS_STAGE1 = 411_000; // kg
    public static final double PROP_MASS_STAGE2 = 107_500; // kg
    public static final double DRY_MASS_STAGE1 = 25_600; // kg
    public static final double DRY_MASS_STAGE2 = 3_900; // kg
    public static final double TOTAL_PROP_MASS = PROP_MASS_STAGE1 + PROP_MASS_STAGE2;
    public static final double THRUST_STAGE1 = 7_607_000; // N
    public static final double THRUST_STAGE2 = 934_000; // N
    public static final double ISP_STAGE1 = 296; // s (avg of SL and vac)
    public static final double ISP_STAGE2 = 348; // s
    public static final double G0 = 9.81; // m/s^2
    public static final double EARTH_RADIUS = 6_371_000; // m
    public static final double ORBIT_ALTITUDE = 200_000; // m (LEO)
    public static final double ORBIT_SPEED = 7_800; // m/s (approx orbital velocity)

    public static class StateUpdate {
        public double currentMass;
        public double remainingPropStage1;
        public double remainingPropStage2;
        public double altitude;
        public double speed;
        public boolean stageChanged;
        public boolean missionEnded;

        public StateUpdate(double currentMass, double remainingPropStage1, double remainingPropStage2,
                           double altitude, double speed, boolean stageChanged, boolean missionEnded) {
            this.currentMass = currentMass;
            this.remainingPropStage1 = remainingPropStage1;
            this.remainingPropStage2 = remainingPropStage2;
            this.altitude = altitude;
            this.speed = speed;
            this.stageChanged = stageChanged;
            this.missionEnded = missionEnded;
        }
    }

    public StateUpdate updatePhysics(long missionTime, int stage, double currentMass,
                                     double remainingPropStage1, double remainingPropStage2,
                                     double altitude, double speed) {
        boolean stageChanged = false;
        boolean missionEnded = false;

        // Select stage parameters
        double thrust = (stage == 1) ? THRUST_STAGE1 : THRUST_STAGE2;
        double isp = (stage == 1) ? ISP_STAGE1 : ISP_STAGE2;
        double dt = 1.0; // 1 second

        // Calculate mass flow rate: mdot = thrust / (ISP * g0)
        double massFlow = thrust / (isp * G0);

        // Update propellant
        if (stage == 1 && remainingPropStage1 > 0) {
            remainingPropStage1 = Math.max(0, remainingPropStage1 - massFlow * dt);
            if (remainingPropStage1 <= 0 && !missionEnded) {
                stageChanged = true;
                currentMass -= DRY_MASS_STAGE1; // Drop stage 1 dry mass
            }
        } else if (stage == 2 && remainingPropStage2 > 0) {
            remainingPropStage2 = Math.max(0, remainingPropStage2 - massFlow * dt);
            if (remainingPropStage2 <= 0) {
                missionEnded = true;
            }
        } else {
            missionEnded = true;
        }

        // Update mass
        currentMass = INITIAL_MASS - (PROP_MASS_STAGE1 - remainingPropStage1) - (PROP_MASS_STAGE2 - remainingPropStage2)
                - (stage == 2 ? DRY_MASS_STAGE1 : 0);

        // Calculate gravity at altitude: g = g0 * (R / (R + h))^2
        double g = G0 * Math.pow(EARTH_RADIUS / (EARTH_RADIUS + altitude), 2);

        // Calculate acceleration: a = thrust / mass - g
        double acceleration = (thrust / currentMass) - g;

        // Update speed: v += a * dt
        speed += acceleration * dt;

        // Update altitude: h += v * dt + 0.5 * a * dt^2 (kinematic)
        altitude += speed * dt + 0.5 * acceleration * dt * dt;

        // Ensure altitude doesn't go negative
        altitude = Math.max(0, altitude);

        return new StateUpdate(currentMass, remainingPropStage1, remainingPropStage2,
                altitude, speed, stageChanged, missionEnded);
    }
}