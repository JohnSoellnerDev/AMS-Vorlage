package de.bs1bt.algorithmen.task3_abfahrtzeiten_verspaetungen;

import java.util.Arrays;

public class FahrzeitAuswertung {
    /**
     * Ermittelt für jede Haltestelle (1..15),
     * wie oft eine relevante Verspätung aufgetreten ist.
     *
     * Relevante Verspätung:
     * |(Ist-Differenz) - (Plan-Differenz)| > 2 Minuten
     *
     * Rückgabe:
     * Index 0 entspricht Haltestelle 1
     * Index 14 entspricht Haltestelle 15
     */
    public static Integer[] ermittleFahrzeiten(Abfahrtszeit[] zeiten) {
        // TODO Aufgabe 3 – Verspätungen ermitteln
        //
        // - Durchlaufen Sie das Array der Abfahrtszeiten.
        // - Vergleichen Sie jeweils zwei aufeinanderfolgende Einträge.
        // - Berücksichtigen Sie nur Abfahrten am selben Datum.
        // - Erhöhen Sie den Zähler der Haltestelle,
        //   wenn |Ist-Differenz − Plan-Differenz| > 2 ist.

        Integer[] verspaetungen = new Integer[15];
        Arrays.fill(verspaetungen, 0);


        return verspaetungen;
    }
}
