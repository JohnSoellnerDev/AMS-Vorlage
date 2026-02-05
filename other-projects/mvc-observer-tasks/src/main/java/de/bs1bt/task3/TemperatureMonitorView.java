package de.bs1bt.task3;

/** OutputView: zeigt immer die aktuelle Temperatur (Pull). */
public class TemperatureMonitorView implements Observer {

    private final TemperatureModel sensor;

    public TemperatureMonitorView(TemperatureModel sensor) {
        this.sensor = sensor;
        this.sensor.addObserver(this); // DI + Registrierung
    }

    @Override
    public void update() {
        System.out.printf("[Temp-Monitor] Aktuelle Temperatur: %.2f °C%n", sensor.getTemperature());
    }
}
