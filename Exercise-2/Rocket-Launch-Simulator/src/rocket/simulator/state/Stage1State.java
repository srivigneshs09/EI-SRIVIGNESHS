package rocket.simulator.state;

import rocket.simulator.model.Rocket;
import rocket.simulator.model.Stage;
import rocket.simulator.singleton.MissionControl;

public class Stage1State implements RocketState {
    private final Rocket rocket;
    private final Stage stage1 = new Stage("Stage 1", 150, 2500.0, 8000.0); // burn rate kg/s
    private int elapsed = 0;

    public Stage1State(Rocket rocket) {
        this.rocket = rocket;
    }

    @Override
    public void onStartChecks() {
        System.out.println("❌ Already launched.");
    }

    @Override
    public void onLaunch() {
        System.out.println("❌ Already in Stage 1.");
    }

    @Override
    public void onFastForward(int seconds) {
        // fast-forward ticks (no sleep)
        for (int i = 0; i < seconds; i++) tick();
    }

    @Override
    public String getName() {
        return "Stage1State";
    }

    private void tick() {
        if (elapsed >= stage1.getBurnTimeSeconds()) {
            System.out.println("🪂 Stage 1 separation. Entering Stage 2...");
            MissionControl.getInstance().setState(new Stage2State(rocket));
            return;
        }

        // update simple physics model (approx)
        rocket.setAltitude(rocket.getAltitude() + 0.5); // km per sec ~ 0.5
        rocket.setSpeed(Math.min(rocket.getSpeed() + 60, stage1.getMaxSpeedKmPerHour())); // km/h gain
        rocket.consumeFuel(stage1.getBurnRateKgPerSec()); // kg consumed per sec
        elapsed++;

        // mission failure condition: out of fuel mid-stage
        if (rocket.getFuel() <= 0) {
            System.out.println("🚨 Mission Failed: Fuel exhausted during Stage 1.");
            MissionControl.getInstance().setState(new MissionFailedState(rocket));
        }
    }
}
