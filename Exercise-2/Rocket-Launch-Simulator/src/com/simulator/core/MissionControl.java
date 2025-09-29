package com.simulator.core;

import com.simulator.states.*;
import com.simulator.utils.Logger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class MissionControl {
    private static volatile MissionControl instance;
    private static final Object lock = new Object();

    private Rocket rocket;
    private TimeManager timeManager;
    private ScheduledExecutorService executor;
    private AtomicBoolean isRunning;
    private volatile boolean missionComplete;

    private MissionControl() {
        this.rocket = new Rocket();
        this.timeManager = new TimeManager();
        this.isRunning = new AtomicBoolean(false);
        this.missionComplete = false;
        this.executor = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "MissionControl-Timer");
            t.setDaemon(true);
            return t;
        });
    }

    public static MissionControl getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new MissionControl();
                }
            }
        }
        return instance;
    }

    public void startMission() {
        if (!isRunning.get() && rocket.getState() instanceof ReadyForLaunchState) {
            isRunning.set(true);
            rocket.setState(new Stage1State());
            timeManager.startMission();
            executor.scheduleAtFixedRate(this::updateSimulation, 0, 1, TimeUnit.SECONDS);
            Logger.success("🚀 LAUNCH SEQUENCE INITIATED");
            displayTelemetry();
        } else {
            Logger.warning("Cannot launch in current state: " + rocket.getState().getStageName());
            Logger.info("Please complete pre-launch checks first.");
        }
    }

    private void updateSimulation() {
        if (isRunning.get() && !missionComplete) {
            synchronized (this) { // Synchronize to prevent concurrent time updates
                timeManager.incrementTime();
                rocket.update(timeManager.getMissionElapsedTime());
                checkStateTransitions();
            }
        }
    }

    private void checkStateTransitions() {
        TelemetryData telemetry = rocket.getTelemetry();
        RocketState currentState = rocket.getState();
        int time = timeManager.getMissionElapsedTime();

        if (currentState instanceof Stage1State && time >= 162) {
            Logger.info("🔄 Stage 1 separation complete. Stage 2 ignition.");
            rocket.setState(new Stage2State());
            rocket.initializeStage2();
            displayTelemetry();
        }

        if (currentState instanceof Stage2State && telemetry.getAltitude() >= 400) {
            rocket.setState(new InOrbitState());
            missionComplete = true;
            Logger.success("🎯 ORBIT ACHIEVED! Mission Successful.");
            displayFinalTelemetry();
        }

        if (telemetry.getFuel() <= 0 && !(currentState instanceof InOrbitState) && !(currentState instanceof FailedState)) {
            rocket.setState(new FailedState());
            Logger.error("❌ Mission Failed due to insufficient fuel.");
            displayTelemetry();
            missionComplete = true;
        }
    }

    public void fastForward(int seconds) {
        if (!isRunning.get()) {
            Logger.warning("Cannot fast-forward. Mission not started.");
            return;
        }
        if (missionComplete) {
            Logger.warning("Cannot fast-forward. Mission already complete.");
            displayTelemetry();
            return;
        }

        Logger.info(String.format("⏩ Fast-forwarding %d seconds...", seconds));
        synchronized (this) { // Synchronize to prevent concurrent updates
            for (int i = 0; i < seconds && !missionComplete; i++) {
                timeManager.incrementTime();
                rocket.update(timeManager.getMissionElapsedTime());
                checkStateTransitions();
                if (rocket.getState() instanceof InOrbitState || rocket.getState() instanceof FailedState) {
                    break;
                }
            }
        }
        displayTelemetry();
    }

    public void displayTelemetry() {
        TelemetryData telemetry = rocket.getTelemetry();
        String stage = rocket.getState().getStageName();
        System.out.printf("T+%ds | Stage: %s | Altitude: %.2f km | Speed: %.0f km/h | Fuel: %.2f%%\n",
                timeManager.getMissionElapsedTime(),
                stage,
                telemetry.getAltitude(),
                telemetry.getSpeed(),
                telemetry.getFuel());
    }

    private void displayFinalTelemetry() {
        TelemetryData telemetry = rocket.getTelemetry();
        int totalTime = timeManager.getMissionElapsedTime();
        System.out.println("\n=== FINAL ORBITAL PARAMETERS ===");
        System.out.printf("Altitude: %.2f km\n", telemetry.getAltitude());
        System.out.printf("Velocity: %.0f km/h\n", telemetry.getSpeed());
        System.out.printf("Mission Duration: %d minutes %d seconds\n", totalTime / 60, totalTime % 60);
        System.out.println("================================\n");
    }

    private void stopMission() {
        isRunning.set(false);
        missionComplete = true;
    }

    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public Rocket getRocket() {
        return rocket;
    }

    public TimeManager getTimeManager() {
        return timeManager;
    }

    public boolean isRunning() {
        return isRunning.get();
    }

    public boolean isMissionComplete() {
        return missionComplete;
    }
}