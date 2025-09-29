package com.simulator.core;

import java.util.concurrent.atomic.AtomicInteger;

public class TimeManager {
    private AtomicInteger missionElapsedTime;
    private long missionStartTime;

    public TimeManager() {
        this.missionElapsedTime = new AtomicInteger(0);
        this.missionStartTime = 0;
    }

    public void startMission() {
        missionStartTime = System.currentTimeMillis();
        missionElapsedTime.set(0);
    }

    public void incrementTime() {
        missionElapsedTime.incrementAndGet();
    }

    public int getMissionElapsedTime() {
        return missionElapsedTime.get();
    }

    public void setMissionElapsedTime(int time) {
        missionElapsedTime.set(time);
    }
}