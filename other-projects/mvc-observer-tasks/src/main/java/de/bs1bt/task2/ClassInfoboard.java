package de.bs1bt.task2;

import java.util.ArrayList;
import java.util.List;

/** Model: speichert letzte Nachricht und benachrichtigt registrierte Views (Push). */
public class ClassInfoboard implements Subject {

    private final List<Observer> observers = new ArrayList<>();
    private String message;

    public void setMessage(String message) {
        this.message = message;
        notifyObservers();
    }
    public String getMessage() {
        return message;
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
            o.update(message);
        }
    }
}
