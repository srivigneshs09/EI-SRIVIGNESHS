package rocket.simulator.state;

import rocket.simulator.model.Rocket;

public class OrbitState implements RocketState {
    private final Rocket rocket;

    public OrbitState(Rocket rocket) {
        this.rocket = rocket;
    }

    @Override
    public void onStartChecks() { System.out.println("✅ Already in orbit."); }

    @Override
    public void onLaunch() { System.out.println("🚀 Mission already completed."); }

    @Override
    public void onFastForward(int seconds) {
        System.out.println("⏩ In orbit. No further stage progression.");
    }

    @Override
    public String getName() {
        return "OrbitState";
    }
}
