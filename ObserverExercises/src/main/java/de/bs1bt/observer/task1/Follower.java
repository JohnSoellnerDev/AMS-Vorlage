package de.bs1bt.observer.task1;

public class Follower {
    private final String name;

    public Follower(String name) {
        this.name = name;
    }

    public void update(String newStatus) {
        System.out.println("Neuer Status: " + newStatus);
    }
}
