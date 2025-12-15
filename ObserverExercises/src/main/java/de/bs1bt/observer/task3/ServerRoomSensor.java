package de.bs1bt.observer.task3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServerRoomSensor implements Subject {
    private final String roomName;
    private double temperature;
    private final List<Observer> observers = new ArrayList<>();

    public ServerRoomSensor(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    public double getTemperature() {
        return temperature;
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
        Iterator<Observer> iterator = observers.iterator();

        while (iterator.hasNext()) {
            Observer observer = iterator.next();
            observer.update();
        }
    }
}
