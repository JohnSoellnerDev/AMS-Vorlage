package de.bs1bt.task3;

public class MainTask3 {
    public static void main(String[] args) {
        TemperatureModel sensor = new TemperatureModel(24.0);

        new TemperatureMonitorView(sensor);
        new WarningMonitorView(sensor, 30.0);

        TemperatureSensor inputView = new TemperatureSensor();
        TemperaturController controller = new TemperaturController(inputView, sensor);

        controller.runLoop();
    }
}
