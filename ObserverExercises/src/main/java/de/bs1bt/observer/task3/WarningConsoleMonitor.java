package de.bs1bt.observer.task3;

public class WarningConsoleMonitor implements Observer {
    private final ServerRoomSensor sensor;

    public WarningConsoleMonitor(ServerRoomSensor sensor) {
        this.sensor = sensor;
        sensor.addObserver(this);
    }

    @Override
    public void update() {
        var temperature = sensor.getTemperature();

        if (temperature >= 30) {
            System.out.println("Alarm es ist zu heiß: " + temperature);
        }
    }
}
