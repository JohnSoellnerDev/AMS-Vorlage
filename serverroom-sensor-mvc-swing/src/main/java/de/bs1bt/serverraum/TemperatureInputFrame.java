package de.bs1bt.serverraum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Eigenes Fenster für die Benutzereingabe.
 * (Input-View)
 */
public class TemperatureInputFrame extends JFrame implements ActionListener {

    private final SensorController controller;
    private JPanel contentPane;

    private final JTextField tfTemperature = new JTextField(10);
    private final JButton btnSet = new JButton("Temperatur setzen");

    public TemperatureInputFrame(SensorController controller) {
        super("Temperatur-Eingabe (View)");

        this.controller = controller;

        contentPane = new JPanel();

        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 8));
        contentPane.setBorder(BorderFactory.createTitledBorder("Eingabe"));

        contentPane.add(new JLabel("Neue Temperatur (°C):"));
        contentPane.add(tfTemperature);
        contentPane.add(btnSet);


        btnSet.addActionListener(this);
        tfTemperature.addActionListener(this);

        tfTemperature.setToolTipText("Beispiel: 24,5 oder 24.5");

        // Wenn die Eingabe geschlossen wird, soll die Demo komplett enden.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setContentPane(contentPane);
        setSize(520, 180);
        setLocationByPlatform(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String raw = tfTemperature.getText();
        try {
            controller.setTemperatureFromUserInput(raw);

            tfTemperature.selectAll();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Bitte eine gültige Zahl eingeben (z. B. 24,5).",
                    "Ungültige Eingabe",
                    JOptionPane.ERROR_MESSAGE
            );
            tfTemperature.requestFocusInWindow();
            tfTemperature.selectAll();
        }
    }
}
