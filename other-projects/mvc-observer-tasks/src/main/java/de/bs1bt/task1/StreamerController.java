package de.bs1bt.task1;

/** Controller: orchestriert den Ablauf (Input lesen -> Model setzen). */
public class StreamerController {

    public Streamer model;
    public StreamerInputView inputView;

    public StreamerController(Streamer model, StreamerInputView inputView) {
        this.model = model;
        this.inputView = inputView;
    }

    public boolean checkUserInput() {
        String status = inputView.readStatus();
        if (status.equalsIgnoreCase("exit")) {  // Programm beenden, wenn 'exit' eingegeben wurde
            return false;
        }

        model.setStatus(status);
        return true;
    }

    /** Diese Methode läuft in Dauerschleife und fragt den Benutzer nach neuen Statuswerten.
     * Das Programm wird beendet, wenn der Benutzer 'exit' eingibt.
     */
    public void runStatusLoop() {
        System.out.println("Task1 gestartet. Hilfe: 'exit' beendet.");

        while (checkUserInput()) {}

        System.out.println("Task1 beendet.");
        System.exit(0);
    }
}
