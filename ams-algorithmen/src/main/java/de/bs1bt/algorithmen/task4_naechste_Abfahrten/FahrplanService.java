package de.bs1bt.algorithmen.task4_naechste_Abfahrten;

public class FahrplanService {

    public Abfahrt[] getAbfahrten(int haltestellenId) {
        // Beispieldaten (sortiert nach Zeit), wie in der Aufgabenlogik erwartet
        return new Abfahrt[] {
                new Abfahrt(1001, 0, DateTime.of(2024, 12, 1, 10,  0)),
                new Abfahrt(1002, 0, DateTime.of(2024, 12, 1, 10, 15)),
                new Abfahrt(1003, 1, DateTime.of(2024, 12, 1, 10, 30)),
                new Abfahrt(1004, 1, DateTime.of(2024, 12, 1, 10, 45)),
                new Abfahrt(1005, 2, DateTime.of(2024, 12, 1, 11,  0)),
        };
    }
}

