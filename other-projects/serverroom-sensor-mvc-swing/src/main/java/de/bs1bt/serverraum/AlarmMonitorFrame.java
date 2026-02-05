package de.bs1bt.serverraum;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Eigenes Fenster für den Alarmmonitor.
 * (Output-View)
 * todo Kommentiere: Welche Rolle übernimmt PropertyChangeListener in Bezug zum Observer-Pattern? Observer oder Subject?
 *                    Wie registriert sich AlarmMonitorFrame als Observer auf dem ServerRoomSensor?
 *                    Wie wird AlarmMonitorFrame über die neue Temperatur informiert?
 */
public class AlarmMonitorFrame extends JFrame implements PropertyChangeListener {

    private final ServerRoomSensor sensor;
    private final double threshold;


    private JPanel contentPane;
    private final JLabel lblStatus = new JLabel("Status: OK", SwingConstants.CENTER);
    private final JLabel lblInfo = new JLabel("", SwingConstants.CENTER);

    public AlarmMonitorFrame(ServerRoomSensor sensor, double threshold) {
        super("Alarmmonitor (View)");

        contentPane = new JPanel();

        this.sensor = sensor;

        // todo Kommentiere: Was geschieht hier mit Bezug zum Observer-Pattern?
        this.sensor.addPropertyChangeListener(this);
        this.threshold = threshold;

        contentPane.setBorder(BorderFactory.createTitledBorder("Alarm-Monitor"));
        contentPane.setLayout(new java.awt.GridLayout(2, 1));

        lblStatus.setFont(lblStatus.getFont().deriveFont(Font.BOLD, 22f));
        lblInfo.setFont(lblInfo.getFont().deriveFont(Font.PLAIN, 14f));

        contentPane.add(lblStatus);
        contentPane.add(lblInfo);

        updateStatus(sensor.getTemperature());

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setContentPane(contentPane);
        setSize(520, 260);
        setLocationByPlatform(true);
    }

    // todo Wie wird AlarmMonitorFrame über die neue Temperatur informiert?
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (ServerRoomSensor.PROP_TEMPERATURE.equals(evt.getPropertyName())) {
            updateStatus(sensor.getTemperature());
        }
    }

    private void updateStatus(double t) {
        if (t >= threshold) {
            lblStatus.setText("ALARM");
            lblInfo.setText(String.format("Temperatur %.1f °C ≥ Schwelle %.1f °C", t, threshold));
            lblStatus.setToolTipText("Achtung: Serverraum zu warm!");
        } else {
            lblStatus.setText("Status: OK");
            lblInfo.setText(String.format("Schwelle: %.1f °C (aktuell %.1f °C)", threshold, t));
            lblStatus.setToolTipText("Temperatur im Normalbereich.");
        }
    }
}
