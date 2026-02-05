package de.bs1bt.algorithmen.task3_abfahrtzeiten_verspaetungen;

import java.time.LocalDate;

import java.time.LocalDate;

public class Abfahrtszeit {

    private final LocalDate datum;
    private final int haltestellenNr;   // 1..15
    private final int planAbfahrt;      // Minuten seit Mitternacht
    private final int istAbfahrt;       // Minuten seit Mitternacht

    public Abfahrtszeit(LocalDate datum, int haltestellenNr,
                        int planAbfahrt, int istAbfahrt) {
        this.datum = datum;
        this.haltestellenNr = haltestellenNr;
        this.planAbfahrt = planAbfahrt;
        this.istAbfahrt = istAbfahrt;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public int getHaltestellenNr() {
        return haltestellenNr;
    }

    public int getPlanAbfahrt() {
        return planAbfahrt;
    }

    public int getIstAbfahrt() {
        return istAbfahrt;
    }
}
