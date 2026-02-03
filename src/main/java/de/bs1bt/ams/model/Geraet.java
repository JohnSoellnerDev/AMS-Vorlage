package de.bs1bt.ams.model;

import jakarta.persistence.*;

/**
 * Repräsentiert ein Gerät in einem Raum.
 */
@Entity
@Table(name = "geraete")
public class Geraet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "geraet_id")
    private int id;

    @Column(name = "bezeichnung")
    private String bezeichnung;

    @Column(name = "typ")
    private String typ;

    @Column(name = "raum_id")
    private Integer raumId;

    @Column(name = "inventarnummer")
    private String inventarnummer;

    public Geraet() {
    }

    public Geraet(String bezeichnung, String typ) {
        setId(-1);
        setBezeichnung(bezeichnung);
        setTyp(typ);
        setRaumId(null);
        setInventarnummer("");
    }

    public Geraet(int id, String bezeichnung, String typ, Integer raumId, String inventarnummer) {
        setId(id);
        setBezeichnung(bezeichnung);
        setTyp(typ);
        setRaumId(raumId);
        setInventarnummer(inventarnummer);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Integer getRaumId() {
        return raumId;
    }

    public void setRaumId(Integer raumId) {
        this.raumId = raumId;
    }

    public String getInventarnummer() {
        return inventarnummer;
    }

    public void setInventarnummer(String inventarnummer) {
        this.inventarnummer = inventarnummer;
    }

    @Override
    public String toString() {
        return "Geraet{" +
                "id=" + id +
                ", bezeichnung='" + bezeichnung + '\'' +
                ", typ='" + typ + '\'' +
                ", raumId=" + raumId +
                ", inventarnummer='" + inventarnummer + '\'' +
                '}';
    }
}
