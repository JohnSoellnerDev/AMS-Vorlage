package de.bs1bt.task3;

public class TemperaturController {
    public TemperatureSensor inputView;
    public TemperatureModel model;

    public TemperaturController(TemperatureSensor inputView, TemperatureModel model) {
        this.inputView = inputView;
        this.model = model;
    }

    public boolean checkSensorInput() {
        Double t = inputView.readTemperature();
        if (t == null) {
            return false;
        }
        model.setTemperature(t);
        return true;
    }

    public void runLoop() {
        System.out.println("Task3 gestartet. Tipp: 'exit' beendet.");
        while (checkSensorInput()) { }
        System.out.println("Task3 beendet.");
    }
}
