package com.simulator.physics;

import com.simulator.core.TelemetryData;
import com.simulator.physics.strategies.StageUpdateStrategy;
import com.simulator.physics.strategies.Stage1UpdateStrategy;
import com.simulator.physics.strategies.Stage2UpdateStrategy;

public class PhysicsEngine {
    private StageUpdateStrategy stage1Strategy;
    private StageUpdateStrategy stage2Strategy;

    public PhysicsEngine() {
        this.stage1Strategy = new Stage1UpdateStrategy();
        this.stage2Strategy = new Stage2UpdateStrategy();
    }

    public void updateTelemetry(TelemetryData telemetry, int missionTime, int stage) {
        if (stage == 0) return;

        StageUpdateStrategy strategy = getStrategyForStage(stage);
        if (strategy != null) {
            strategy.update(telemetry, missionTime);
        }
    }

    private StageUpdateStrategy getStrategyForStage(int stage) {
        switch (stage) {
            case 1: return stage1Strategy;
            case 2: return stage2Strategy;
            default: return null;
        }
    }
}