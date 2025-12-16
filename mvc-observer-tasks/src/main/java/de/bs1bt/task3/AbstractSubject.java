package de.bs1bt.task3;

import java.util.ArrayList;
import java.util.List;

/** Gemeinsame Subject-Logik für Pull-Verfahren. */
public abstract class AbstractSubject implements Subject {
    private final List<Observer> observers = new ArrayList<>();

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
            o.update();
        }
    }
}
