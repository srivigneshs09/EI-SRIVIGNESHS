package rocket.simulator.model;

public class Stage {
    private final String name;
    private final int burnTimeSeconds;
    private final double burnRateKgPerSec;
    private final double maxSpeedKmPerHour;

    public Stage(String name, int burnTimeSeconds, double burnRateKgPerSec, double maxSpeedKmPerHour) {
        this.name = name;
        this.burnTimeSeconds = burnTimeSeconds;
        this.burnRateKgPerSec = burnRateKgPerSec;
        this.maxSpeedKmPerHour = maxSpeedKmPerHour;
    }

    public String getName() { return name; }
    public int getBurnTimeSeconds() { return burnTimeSeconds; }
    public double getBurnRateKgPerSec() { return burnRateKgPerSec; }
    public double getMaxSpeedKmPerHour() { return maxSpeedKmPerHour; }

    @Override
    public String toString() {
        return "Stage{" +
                "name='" + name + '\'' +
                ", burnTimeSeconds=" + burnTimeSeconds +
                ", burnRateKgPerSec=" + burnRateKgPerSec +
                ", maxSpeedKmPerHour=" + maxSpeedKmPerHour +
                '}';
    }
}
