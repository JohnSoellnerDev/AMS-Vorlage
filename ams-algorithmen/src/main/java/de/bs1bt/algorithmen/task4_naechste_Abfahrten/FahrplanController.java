package de.bs1bt.algorithmen.task4_naechste_Abfahrten;

import java.util.Arrays;

public class FahrplanController {

    private final FahrplanService fahrplanService;

    public FahrplanController(FahrplanService fahrplanService) {
        this.fahrplanService = fahrplanService;
    }

    // Aufgabe 2 b): nächste Abfahrten ab jetzt, maximal maxAbfahrten
    public Abfahrt[] getNaechsteAbfahrten(int haltestellenId, int maxAbfahrten) {
        // TODO Aufgabe 4 – Nächste Abfahrten
        //
        // - Ermitteln Sie die nächsten Abfahrten ab dem jetzigen (Fake-)Zeitpunkt: DateTime jetzt = DateTime.now();
        // - Berücksichtigen Sie maximal maxAbfahrten.
        // - Verwenden Sie die compare()-Methode von DateTime.
        // - Brechen Sie ab, sobald das Ergebnisarray gefüllt ist.

        Abfahrt[] naechste = new Abfahrt[maxAbfahrten];


        return naechste;
    }

}
