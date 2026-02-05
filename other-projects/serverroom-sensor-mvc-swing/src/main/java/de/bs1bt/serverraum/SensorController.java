package de.bs1bt.serverraum;

/**
 * Controller: Nimmt Eingaben entgegen und schreibt Änderungen ins Model.
 */
public class SensorController {

    private final ServerRoomSensor model;

    public SensorController(ServerRoomSensor model) {
        this.model = model;
    }

    /**
     * todo Wird von der InputView aufgerufen, wenn der Benutzer eine neue Temperatur setzen möchte.
     * Der Controller erledigt das Parsing und die Validierung der Benutzer-Eingaben:
     * - Warum ist Validierung hier im Controller (und nicht in der View oder erst im Model)?
     * - Welche Vorteile hat das
     *    - beim Einsatz mehrerer Views und
     *    - für die Testbarkeit ?
     */
    public void setTemperatureFromUserInput(String rawText) throws NumberFormatException {
        if (rawText == null) {
            throw new NumberFormatException("Eingabe ist leer.");
        }

        String trimmed = rawText.trim().replace(',', '.');

        if (trimmed.isEmpty()) {
            throw new NumberFormatException("Eingabe ist leer.");
        }

        double value = Double.parseDouble(trimmed);
        model.setTemperature(value);
    }
}
