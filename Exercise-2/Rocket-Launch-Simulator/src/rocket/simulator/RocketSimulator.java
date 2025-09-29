package rocket.simulator;

import rocket.model.RocketPhysics;
import rocket.state.RocketState;
import rocket.state.PreLaunchState;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class RocketSimulator {
    private static final Logger LOGGER = Logger.getLogger(RocketSimulator.class.getName());
    private static RocketSimulator instance;

    // Simulation variables
    private RocketState currentState;
    private long missionTime = 0; // seconds
    private double altitude = 0; // meters
    private double speed = 0; // m/s
    private int stage = 0; // 0: pre-launch, 1: stage 1, 2: stage 2
    private double remainingPropStage1; // kg
    private double remainingPropStage2; // kg
    private double currentMass; // kg
    private double fuelPercent; // %
    private boolean missionEnded = false;
    private ScheduledExecutorService scheduler;
    private final Scanner scanner;
    private final RocketPhysics physics;

    // Singleton
    private RocketSimulator() {
        currentState = new PreLaunchState(this);
        scanner = new Scanner(System.in);
        physics = new RocketPhysics();
        initializeRocket();
    }

    public static RocketSimulator getInstance() {
        if (instance == null) {
            instance = new RocketSimulator();
        }
        return instance;
    }

    private void initializeRocket() {
        remainingPropStage1 = RocketPhysics.PROP_MASS_STAGE1;
        remainingPropStage2 = RocketPhysics.PROP_MASS_STAGE2;
        currentMass = RocketPhysics.INITIAL_MASS;
        fuelPercent = 100.0;
        stage = 0;
    }

    public void tick() {
        if (missionEnded) return;
        missionTime++;
        RocketPhysics.StateUpdate update = physics.updatePhysics(
                missionTime, stage, currentMass, remainingPropStage1, remainingPropStage2, altitude, speed
        );
        currentMass = update.currentMass;
        remainingPropStage1 = update.remainingPropStage1;
        remainingPropStage2 = update.remainingPropStage2;
        altitude = update.altitude;
        speed = update.speed;
        fuelPercent = ((remainingPropStage1 + remainingPropStage2) / RocketPhysics.TOTAL_PROP_MASS) * 100;

        if (update.stageChanged) {
            stage = 2;
            System.out.println("Stage 1 complete. Separating stage. Entering Stage 2.");
            printState();
            LOGGER.info("Stage separation at T+" + missionTime + "s");
        }

        if (update.missionEnded) {
            missionEnded = true;
            printState();
            if (altitude >= RocketPhysics.ORBIT_ALTITUDE && speed >= RocketPhysics.ORBIT_SPEED) {
                System.out.println("Orbit achieved! Mission Successful.");
                LOGGER.info("Mission successful at T+" + missionTime + "s");
            } else {
                System.out.println("Mission Failed due to insufficient fuel.");
                LOGGER.severe("Mission failed at T+" + missionTime + "s");
            }
            stopSimulation();
        }
    }

    public void fastForward(long seconds) {
        if (seconds <= 0) {
            throw new IllegalArgumentException("Fast forward seconds must be positive.");
        }
        stopSimulation();
        for (long i = 0; i < seconds && !missionEnded; i++) {
            missionTime++;
            RocketPhysics.StateUpdate update = physics.updatePhysics(
                    missionTime, stage, currentMass, remainingPropStage1, remainingPropStage2, altitude, speed
            );
            currentMass = update.currentMass;
            remainingPropStage1 = update.remainingPropStage1;
            remainingPropStage2 = update.remainingPropStage2;
            altitude = update.altitude;
            speed = update.speed;
            fuelPercent = ((remainingPropStage1 + remainingPropStage2) / RocketPhysics.TOTAL_PROP_MASS) * 100;

            if (update.stageChanged) {
                stage = 2;
                System.out.println("Stage 1 complete. Separating stage. Entering Stage 2.");
                printState();
                LOGGER.info("Stage separation at T+" + missionTime + "s");
            }

            if (update.missionEnded) {
                missionEnded = true;
                printState();
                if (altitude >= RocketPhysics.ORBIT_ALTITUDE && speed >= RocketPhysics.ORBIT_SPEED) {
                    System.out.println("Orbit achieved! Mission Successful.");
                    LOGGER.info("Mission successful at T+" + missionTime + "s");
                } else {
                    System.out.println("Mission Failed due to insufficient fuel.");
                    LOGGER.severe("Mission failed at T+" + missionTime + "s");
                }
                return;
            }
        }
        printState();
        if (!missionEnded) {
            startSimulation();
        }
    }

    public void printState() {
        System.out.printf("T+%d s, Stage: %d, Fuel: %.1f%%, Altitude: %.1f km, Speed: %.1f km/h%n",
                missionTime, stage, fuelPercent, altitude / 1000, speed * 3.6);
    }

    public void changeState(RocketState newState) {
        LOGGER.info("Changing state from " + currentState.getClass().getSimpleName() + " to " + newState.getClass().getSimpleName());
        currentState = newState;
        currentState.onEnter();
    }

    public void startSimulation() {
        if (scheduler == null || scheduler.isShutdown()) {
            scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(this::tick, 1, 1, TimeUnit.SECONDS);
            LOGGER.info("Simulation started at T+" + missionTime + "s");
        }
    }

    public void stopSimulation() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(2, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
                Thread.currentThread().interrupt();
            }
            LOGGER.info("Simulation stopped at T+" + missionTime + "s");
        }
    }

    public void exit() {
        missionEnded = true;
        scanner.close();
        stopSimulation();
        System.out.println("👋 Exiting simulation. Goodbye!");
    }

    // Getters
    public Scanner getScanner() {
        return scanner;
    }

    public RocketState getCurrentState() {
        return currentState;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public long getMissionTime() {
        return missionTime;
    }

    public void setMissionTime(long missionTime) {
        this.missionTime = missionTime;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isMissionEnded() {
        return missionEnded;
    }
}