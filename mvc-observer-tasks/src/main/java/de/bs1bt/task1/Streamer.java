package de.bs1bt.task1;

import java.util.ArrayList;
import java.util.List;

public class Streamer implements Subject {
    private String status;
    private final List<Observer> observers = new ArrayList<>();

    public Streamer(String initialStatus) {
        this.status = initialStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null) return;
        this.status = status;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer o) {
        if (o != null && !observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(status);
        }
    }
}
