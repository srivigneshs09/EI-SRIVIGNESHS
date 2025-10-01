package com.simulator.physics.strategies;

import com.simulator.core.TelemetryData;

public interface StageUpdateStrategy {
    void update(TelemetryData telemetry, int missionTime);
}