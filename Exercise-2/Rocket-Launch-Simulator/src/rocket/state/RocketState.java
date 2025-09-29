package rocket.state;

public interface RocketState {
    void processInput(String input);
    void onEnter();
}