package de.bs1bt.task2;

/** OutputView: zeigt alle Nachrichten an. */
public class InfoChannelView implements Observer {
    @Override
    public void update(String message) {
        System.out.println("[Info-Nachrichten-Kanal] " + message);
    }
}
