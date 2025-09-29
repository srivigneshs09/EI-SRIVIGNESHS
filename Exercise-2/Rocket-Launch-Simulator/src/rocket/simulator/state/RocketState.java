package rocket.simulator.state;

public interface RocketState {
    void onStartChecks();
    void onLaunch();
    void onFastForward(int seconds);
    String getName();
}
