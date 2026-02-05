package de.bs1bt.task2;

/** Controller: orchestriert den Ablauf (Input lesen -> Model setzen). */
public class InfoboardController {

    private final ClassInfoboard model;
    private final MessageInputView inputView;

    public InfoboardController(ClassInfoboard model, MessageInputView inputView) {
        this.model = model;
        this.inputView = inputView;
    }

    public boolean checkUserInput() {
        String msg = inputView.readMessage();
        if (msg.equalsIgnoreCase("exit")) {
            return false;
        }
        model.setMessage(msg);
        return true;
    }

    public void runLoop() {
        System.out.println("Task2 gestartet. Tipp: 'exit' beendet.");
        while (checkUserInput()) { }
        System.out.println("Task2 beendet.");
    }
}

