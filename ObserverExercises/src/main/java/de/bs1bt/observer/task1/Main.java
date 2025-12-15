package de.bs1bt.observer.task1;

public class Main {
    public static void main(String[] args) {
        Streamer streamer = new Streamer("Streamer");
        Follower follower1 = new Follower("Follower 1");

        streamer.setFollower(follower1);

        streamer.setStatus("Online");
    }
}
