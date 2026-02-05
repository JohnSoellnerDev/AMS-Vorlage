package de.bs1bt.observer.task2;

public class ConsoleInfoChannel implements Observer {
    private final String name;

    public ConsoleInfoChannel(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + ": " + message);
    }
}
