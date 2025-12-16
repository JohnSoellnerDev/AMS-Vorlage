package de.bs1bt.algorithmen.task1_raumbelegung_sortieren;

public class Raum {
    private final String raumNr;
    private final String gebaeude;
    private final int belegung;
    private final boolean belegt;

    public Raum(String raumNr, String gebaeude, int belegung, boolean belegt) {
        this.raumNr = raumNr;
        this.gebaeude = gebaeude;
        this.belegung = belegung;
        this.belegt = belegt;
    }

    public String getRaumNr() {
        return raumNr;
    }

    public String getGebaeude() {
        return gebaeude;
    }

    public Integer getBelegung() {
        return belegung;
    }

    public boolean isBelegt() {
        return belegt;
    }

    @Override
    public String toString() {
        return "Raum{" +
                "raumNr='" + raumNr +
                ", gebaeude=" + gebaeude +
                ", belegung=" + belegung +
                ", belegt=" + belegt +
                '}';
    }
}
