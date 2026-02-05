package de.bs1bt.task3;

/** Model: Temperatur + Benachrichtigung (Pull). */
public class TemperatureModel extends AbstractSubject {
    private double temperature;

    public TemperatureModel(double initialTemperature) {
        this.temperature = initialTemperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        notifyObservers();
    }
}
