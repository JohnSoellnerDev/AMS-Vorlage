package de.bs1bt.observer.task2;

public class WarningChannel implements Observer {
    private final String name;

    public WarningChannel(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        if (message != null && message.contains("WICHTIG")) {
            System.out.println(name + " (wichtig): " + message);
        }
    }
}
