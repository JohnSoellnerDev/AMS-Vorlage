package de.bs1bt.observer.task3;

public class Main {
    public static void main(String[] args) {
        ServerRoomSensor sensor = new ServerRoomSensor("Serverraum 1");

        new TemperatureConsoleMonitor(sensor);
        new WarningConsoleMonitor(sensor);

        sensor.setTemperature(22.5);
        sensor.setTemperature(28.0);
        sensor.setTemperature(31.7);
    }
}
