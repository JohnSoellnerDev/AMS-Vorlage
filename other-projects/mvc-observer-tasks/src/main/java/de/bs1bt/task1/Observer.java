package de.bs1bt.task1;

/** Push-Observer: Das Subject liefert die neuen Daten direkt mit. */
public interface Observer {
    void update(String newStatus);
}
