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
        // - Vergleichen Sie jeweils zwei aufeinanderfolgende Einträge.
        // - Berücksichtigen Sie nur Abfahrten am selben Datum.
        // - Erhöhen Sie den Zähler der Haltestelle,
        //   wenn |Ist-Differenz − Plan-Differenz| > 2 ist.

        Integer[] verspaetungen = new Integer[15];
        Arrays.fill(verspaetungen, 0);

        // - Durchlaufen Sie das Array der Abfahrtszeiten.
        for (int i = 0; i < zeiten.length - 1; i++) {
            Abfahrtszeit start = zeiten[i];
            Abfahrtszeit ende = zeiten[i + 1];

            // Nur Abfahrten am selben Datum vergleichen
            if (!start.getDatum().equals(ende.getDatum())) {
                continue;
            }

            int planDauer = ende.getPlanAbfahrt() - start.getPlanAbfahrt();
            int istDauer = ende.getIstAbfahrt() - start.getIstAbfahrt();

            if (Math.abs(istDauer - planDauer) > 2) {
                verspaetungen[ende.getHaltestellenNr() - 1]++;
            }
        }

        return verspaetungen;
    }
}
