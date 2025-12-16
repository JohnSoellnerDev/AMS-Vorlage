package de.bs1bt.task2;

/** OutputView: zeigt nur Nachrichten, die 'WICHTIG' enthalten. */
public class WarningChannelView implements Observer {
    @Override
    public void update(String message) {
        if (message != null && message.toUpperCase().contains("WICHTIG")) {
            System.out.println("[WARN-Kanal] " + message);
        }
    }
}
