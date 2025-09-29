package rocket.simulator.state;

import rocket.simulator.model.Rocket;
import rocket.simulator.model.Stage;
import rocket.simulator.singleton.MissionControl;

public class Stage2State implements RocketState {
    private final Rocket rocket;
    private final Stage stage2 = new Stage("Stage 2", 360, 400.0, 28000.0); // burn rate kg/s
    private int elapsed = 0;

    public Stage2State(Rocket rocket) {
        this.rocket = rocket;
    }

    @Override
    public void onStartChecks() { System.out.println("❌ Already launched."); }

    @Override
    public void onLaunch() { System.out.println("❌ Already in flight."); }

    @Override
    public void onFastForward(int seconds) {
        for (int i = 0; i < seconds; i++) tick();
    }

    @Override
    public String getName() {
        return "Stage2State";
    }

    private void tick() {
        if (elapsed >= stage2.getBurnTimeSeconds()) {
            System.out.println("🛰 Orbit achieved! Mission success 🎉");
            MissionControl.getInstance().setState(new OrbitState(rocket));
            return;
        }

        rocket.setAltitude(rocket.getAltitude() + 0.33); // km per sec approx
        rocket.setSpeed(Math.min(rocket.getSpeed() + 55, stage2.getMaxSpeedKmPerHour()));
        rocket.consumeFuel(stage2.getBurnRateKgPerSec());
        elapsed++;

        if (rocket.getFuel() <= 0) {
            System.out.println("🚨 Mission Failed: Fuel exhausted during Stage 2.");
            MissionControl.getInstance().setState(new MissionFailedState(rocket));
        }
    }
}
