package de.bs1bt.task3;

import de.bs1bt.common.Console;

/** InputView: liest neue Temperatur von der Konsole. */
public class TemperatureSensor {

    public String readRaw() {
        return Console.readLine("Neue Temperatur (oder 'exit'): ").trim();
    }

    public Double readTemperature() {
        String raw = readRaw();
        if (raw.equalsIgnoreCase("exit")) {
            return null;
        }

        try {
            return Double.parseDouble(raw.replace(",", "."));
        } catch (NumberFormatException ex) {
            System.out.println("Ungültige Zahl. Bitte erneut eingeben.");
            return readTemperature();
        }
    }
}
