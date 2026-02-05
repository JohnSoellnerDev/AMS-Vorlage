package de.bs1bt.algorithmen.task4_naechste_Abfahrten;

import java.time.LocalDateTime;


// Minimaler DateTime-Wrapper passend zur Aufgaben-API-Idee (now(), compare())
public class DateTime {
    private final LocalDateTime value;

    public DateTime(LocalDateTime value) {
        this.value = value;
    }

    public static DateTime now() {
        // fake für Tests
        return DateTime.of(2024, 12, 1, 10, 20);
    }

    /**
     * Rückgabe wie in der Aufgabenbeschreibung:
     *  0 => gleich, -1 => dieses Objekt ist vor "other", 1 => dieses Objekt ist nach "other"
     */
    public int compare(DateTime other) {
        int c = this.value.compareTo(other.value);
        return Integer.compare(c, 0);
    }

    public static DateTime of(int year, int month, int day, int hour, int minute) {
        return new DateTime(LocalDateTime.of(year, month, day, hour, minute));
    }

    @Override
    public String toString() {
        return value.toString();
    }
}