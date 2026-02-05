package de.bs1bt.algorithmen.task2_geraete_gruppieren;

public class Geraet {
    private final int inventarnummer;
    private final String bezeichnung;
    private final boolean defekt;

    public Geraet(int inventarnummer, String bezeichnung, boolean defekt) {
        this.inventarnummer = inventarnummer;
        this.bezeichnung = bezeichnung;
        this.defekt = defekt;
    }

    public int getInventarnummer() {
        return inventarnummer;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public boolean isDefekt() {
        return defekt;
    }

    @Override
    public String toString() {
        return "Geraet{" +
                "inventarnummer=" + inventarnummer +
                ", bezeichnung=" + bezeichnung +
                ", defekt=" + defekt +
                '}';
    }
}
