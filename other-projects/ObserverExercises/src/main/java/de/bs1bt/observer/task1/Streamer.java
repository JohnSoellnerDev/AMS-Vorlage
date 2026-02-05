package de.bs1bt.observer.task1;

public class Streamer {
    private final String name;
    private String status;
    private Follower follower; // genau ein Observer

    public Streamer(String name) {
        this.name = name;
    }

    public void setFollower(Follower follower) {
        this.follower = follower;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("[" + name + "] neuer Status: " + newStatus);
        follower.update(newStatus);
    }

    public String getStatus() {
        return status;
    }
}
