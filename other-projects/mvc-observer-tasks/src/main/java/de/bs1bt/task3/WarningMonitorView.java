package de.bs1bt.task3;

/** OutputView: löst Alarm aus, wenn Schwelle überschritten ist (Pull). */
public class WarningMonitorView implements Observer {

    private final TemperatureModel sensor;
    private final double threshold;

    public WarningMonitorView(TemperatureModel sensor, double threshold) {
        this.sensor = sensor;
        this.threshold = threshold;
        this.sensor.addObserver(this); // DI + Registrierung
    }

    @Override
    public void update() {
        double t = sensor.getTemperature();
        if (t >= threshold) {
            System.out.printf("[WarningMonitor: ALARM] Temperatur %.2f °C überschreitet Schwelle %.2f °C!%n", t, threshold);
        } else {
            System.out.printf("[WarningMonitor: OK] (%.2f °C < %.2f °C)%n", t, threshold);
        }
    }
}
