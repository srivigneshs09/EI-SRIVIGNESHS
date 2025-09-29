package rocket.simulator.state;

import rocket.simulator.model.Rocket;
import rocket.simulator.singleton.MissionControl;

public class PreLaunchState implements RocketState {
    private final Rocket rocket;

    public PreLaunchState(Rocket rocket) {
        this.rocket = rocket;
    }

    @Override
    public void onStartChecks() {
        // Keep this simple — print checks OK. You can add failure simulation later.
        System.out.println("✅ Running pre-launch checks...");
        System.out.println("[Check] Engines: OK");
        System.out.println("[Check] Telemetry: OK");
        System.out.println("[Check] Fuel systems: OK");
        System.out.println("✅ All systems GO for launch!");
    }

    @Override
    public void onLaunch() {
        System.out.println("🚀 Launch initiated: Stage 1 ignition");
        MissionControl.getInstance().setState(new Stage1State(rocket));
    }

    @Override
    public void onFastForward(int seconds) {
        System.out.println("⏩ Cannot fast forward before launch.");
    }

    @Override
    public String getName() {
        return "PreLaunchState";
    }
}
