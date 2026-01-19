package de.bs1bt.algorithmen.task2_geraete_gruppieren;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Geraet[] geraete = {
            new Geraet(1, "Drucker", false),
            new Geraet(12, "Drucker", false),
            new Geraet(5, "Drucker", true),
            new Geraet(3, "Monitor", false),
            new Geraet(7, "Monitor", false),
            new Geraet(8, "Netzwerkgerät", true),
            new Geraet(42, "Netzwerkgerät", false)
        };

        System.out.println("GERÄTE VOR DEM SORTIEREN");
        for (Geraet g : geraete) {
            System.out.println(g);
        }

        GeraeteService.orderByBezeichnung(geraete);

        System.out.println("\nGERÄTE NACH DEM SORTIEREN (nach Bezeichnung, dann Inventarnummer)");
        for (Geraet g : geraete) {
            System.out.println(g);
        }

        GeraeteService.printBerichtA(geraete);
        GeraeteService.printBerichtB(geraete);
        GeraeteService.printBerichtC(geraete);

        System.out.println("\nAlle Berichte wurden erstellt!");
    }
}

