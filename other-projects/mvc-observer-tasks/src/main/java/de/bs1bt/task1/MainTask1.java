package de.bs1bt.task1;

public class MainTask1 {
    public static void main(String[] args) {
        Streamer streamer = new Streamer("Startet gleich");

        FollowerView followerView = new FollowerView("Alex");
        streamer.addObserver(followerView);

        // Hilfsklasse zum Einlesen der Benutzereingaben
        StreamerInputView inputView = new StreamerInputView();

        // Controller mit Model und InputView verbinden
        StreamerController controller = new StreamerController(streamer, inputView);

        controller.runStatusLoop();
    }
}
