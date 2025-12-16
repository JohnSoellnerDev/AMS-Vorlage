package de.bs1bt.serverraum;

import javax.swing.SwingUtilities;

/**
 * Startpunkt der Demo-Anwendung (MVC + Observer/Pull via PropertyChange).
 */
public class Main {

    public static void main(String[] args) {
        // UI-Updates laufen im Event Dispatch Thread – daher Start via invokeLater.
        SwingUtilities.invokeLater(() -> {
            ServerRoomSensor model = new ServerRoomSensor();
            SensorController controller = new SensorController(model);

            // Jede View läuft in einem eigenen Fenster:
            TemperatureMonitorFrame monitorFrame = new TemperatureMonitorFrame(model);
            AlarmMonitorFrame alarmFrame = new AlarmMonitorFrame(model, 30);
            TemperatureInputFrame inputFrame = new TemperatureInputFrame(controller);

            monitorFrame.setVisible(true);
            alarmFrame.setVisible(true);
            inputFrame.setVisible(true);
        });
    }
}
