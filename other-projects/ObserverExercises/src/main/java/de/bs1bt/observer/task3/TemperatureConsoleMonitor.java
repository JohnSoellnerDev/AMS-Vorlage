package de.bs1bt.observer.task3;

public class TemperatureConsoleMonitor implements Observer {
    private final ServerRoomSensor sensor;

    public TemperatureConsoleMonitor(ServerRoomSensor sensor) {
        this.sensor = sensor;
        sensor.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("Temperatur: " + sensor.getTemperature());
    }
}
