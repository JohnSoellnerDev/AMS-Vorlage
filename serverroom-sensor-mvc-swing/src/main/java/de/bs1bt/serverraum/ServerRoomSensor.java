package de.bs1bt.serverraum;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Model: Speichert die aktuelle Temperatur und informiert Views über Änderungen.
 * Benachrichtigung erfolgt über PropertyChangeSupport (Property: "temperature").
 */
public class ServerRoomSensor {

    public static final String PROP_TEMPERATURE = "temperature";

    private double temperature;
    // todo Kommentiere: Welche Rolle übernimmt PropertyChangeSupport in Bezug zum Observer-Pattern? Observer oder Subject?
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double newTemperature) {
        double old = this.temperature;
        this.temperature = newTemperature;

        // todo Kommentiere: Welcher Methode entspricht der folgende Methodenaufruf im Observer-Pattern: notifyObservers(...) oder update(...)?
        pcs.firePropertyChange(PROP_TEMPERATURE, old, newTemperature);
    }

    // todo Kommentiere: Welcher Methode entspricht die folgende Methode im Observer-Pattern?
    // todo Von wem wird die Methode wozu genutzt?
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    // todo Kommentiere: Welcher Methode entspricht die folgende Methode im Observer-Pattern?
    // Wann müsste man remove… aufrufen? Tipp: Denke an Memory Leaks,  lange laufende Apps, viele Views ...
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
}
