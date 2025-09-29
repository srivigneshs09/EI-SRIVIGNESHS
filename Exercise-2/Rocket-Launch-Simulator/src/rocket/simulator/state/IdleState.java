package rocket.simulator.state;

import rocket.simulator.model.Rocket;
import rocket.simulator.singleton.MissionControl;

public class IdleState implements RocketState {

    @Override
    public void onStartChecks() {
        System.out.println("🚀 Initiating pre-launch checks...");
        // Create PreLaunchState with the shared rocket and immediately run checks
        Rocket rocket = MissionControl.getInstance().getRocket();
        PreLaunchState pre = new PreLaunchState(rocket);
        MissionControl.getInstance().setState(pre);
        pre.onStartChecks(); // run actual checks and report
    }

    @Override
    public void onLaunch() {
        System.out.println("❌ Cannot launch! Please run 'start_checks' first.");
    }

    @Override
    public void onFastForward(int seconds) {
        System.out.println("⏩ Cannot fast-forward. Launch has not started yet.");
    }

    @Override
    public String getName() {
        return "IdleState";
    }
}
