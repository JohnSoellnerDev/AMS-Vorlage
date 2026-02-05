package de.bs1bt.task1;

public class FollowerView implements Observer {

    private final String name;

    public FollowerView(String name) {
        this.name = name;
    }

    @Override
    public void update(String newStatus) {
        System.out.printf("[Follower %s] Neuer Streamer-Status: %s%n", name, newStatus);
    }
}
