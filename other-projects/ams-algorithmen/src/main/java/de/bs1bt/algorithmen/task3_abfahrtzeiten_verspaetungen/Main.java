package de.bs1bt.algorithmen.task3_abfahrtzeiten_verspaetungen;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Abfahrtszeit[] daten = new Abfahrtszeit[] {
            // ===== 01.09.2024 =====
            new Abfahrtszeit(LocalDate.of(2024, 9, 1), 1, 480, 480),
            new Abfahrtszeit(LocalDate.of(2024, 9, 1), 2, 483, 489),
            new Abfahrtszeit(LocalDate.of(2024, 9, 1), 3, 485, 491),
            new Abfahrtszeit(LocalDate.of(2024, 9, 1), 4, 488, 497),
            new Abfahrtszeit(LocalDate.of(2024, 9, 1), 5, 490, 497),

            // ===== 02.09.2024 =====
            new Abfahrtszeit(LocalDate.of(2024, 9, 2), 1, 480, 482),
            new Abfahrtszeit(LocalDate.of(2024, 9, 2), 2, 483, 490),
            new Abfahrtszeit(LocalDate.of(2024, 9, 2), 3, 485, 493),
            new Abfahrtszeit(LocalDate.of(2024, 9, 2), 4, 488, 499),
            new Abfahrtszeit(LocalDate.of(2024, 9, 2), 5, 490, 501),
            new Abfahrtszeit(LocalDate.of(2024, 9, 2), 6, 493, 505)
        };

        var ergebnis = FahrzeitAuswertung.ermittleFahrzeiten(daten);

        System.out.println("Verspätungen je Haltestelle:");
        for (int i = 0; i < ergebnis.length; i++) {
            if (ergebnis[i] > 0) {
                System.out.printf("Haltestelle %d: %d%n", i + 1, ergebnis[i]);
            }
        }
    }
}

