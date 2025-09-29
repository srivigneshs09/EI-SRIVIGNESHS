package rocket.simulator.state;

import rocket.simulator.model.Rocket;

public class MissionFailedState implements RocketState {
    private final Rocket rocket;

    public MissionFailedState(Rocket rocket) {
        this.rocket = rocket;
    }

    @Override
    public void onStartChecks() { System.out.println("🚨 Mission failed."); }

    @Override
    public void onLaunch() { System.out.println("🚨 Mission failed."); }

    @Override
    public void onFastForward(int seconds) { System.out.println("🚨 Mission failed."); }

    @Override
    public String getName() {
        return "MissionFailedState";
    }
}
