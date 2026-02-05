package de.bs1bt.observer.task2;

import java.util.ArrayList;
import java.util.List;

public class ClassInfoBoard implements Subject {
    private String message;
    private List<Observer> observers = new ArrayList<>();

    public void setMessage(String message) {
        this.message = message;
        notifyObservers();
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
