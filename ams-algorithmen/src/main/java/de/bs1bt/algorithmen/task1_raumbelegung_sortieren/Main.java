package de.bs1bt.algorithmen.task1_raumbelegung_sortieren;

public class Main {
    public static void main(String[] args) {
        Raum[] raeume = {
            new Raum("A101", "Hauptgebäude", 25, true),
            new Raum("B205", "Nebengebäude", 10, true),
            new Raum("A102", "Hauptgebäude", 30, true),
            new Raum("C301", "Werkstatt", 5, false),
            new Raum("B201", "Nebengebäude", 18, true),
            new Raum("A103", "Hauptgebäude", 12, false)
        };

        System.out.println("RÄUME VOR DEM SORTIEREN");
        for (Raum raum : raeume) {
            System.out.println(raum);
        }

        RaumBelegungsService.sortiereRaumArray(raeume);

        System.out.println("\n RÄUME NACH DEM SORTIEREN (nach Belegung aufsteigend)");
        for (Raum raum : raeume) {
            System.out.println(raum);
        }
    }
}

