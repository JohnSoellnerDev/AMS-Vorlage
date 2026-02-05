package de.bs1bt.serverraum;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * Eigenes Fenster für die Ausgabe der aktuellen Temperatur.
 * (Output-View)
 */
public class TemperatureMonitorFrame extends JFrame implements PropertyChangeListener {

    private final ServerRoomSensor sensor;

    private final JLabel lblValue = new JLabel("-- °C", SwingConstants.CENTER);

    private JPanel contentPane;

    public TemperatureMonitorFrame(ServerRoomSensor sensor) {
        super("Temperaturmonitor (View)");
        contentPane = new JPanel();

        this.sensor = sensor;

        // todo Kommentiere: Was geschieht hier mit Bezug zum Observer-Pattern?
        this.sensor.addPropertyChangeListener(this);

        contentPane.setBorder(BorderFactory.createTitledBorder("Monitor: Aktuelle Temperatur"));

        lblValue.setFont(lblValue.getFont().deriveFont(Font.BOLD, 28f));
        contentPane.add(lblValue);

        updateLabel(sensor.getTemperature());

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setContentPane(contentPane);
        setSize(420, 220);
        setLocationByPlatform(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (ServerRoomSensor.PROP_TEMPERATURE.equals(evt.getPropertyName())) {
            // push
            double newTemperature = (double) evt.getNewValue();
            updateLabel(newTemperature);
        }
    }

    private void updateLabel(double temperature) {
        lblValue.setText(String.format("%.1f °C", temperature));
        lblValue.setToolTipText("Letzter Messwert: " + String.format("%.1f °C", temperature));
    }
}
