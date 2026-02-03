package de.bs1bt.ams.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "gebaeude")
public class Gebaeude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gebaeude_id")
    private int id;

    @Column(name = "bezeichnung")
    private String bezeichnung;

    @Column(name = "adresse")
    private String adresse;

    @OneToMany(mappedBy = "gebaeude", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Raum> raeume = new ArrayList<>();

    public Gebaeude() {
    }

    public Gebaeude(String bezeichnung, String adresse) {
        this.bezeichnung = bezeichnung;
        this.adresse = adresse;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Raum> getRaeume() {
        return raeume;
    }

    public void setRaeume(List<Raum> raeume) {
        this.raeume = raeume;
    }

    public void addRaum(Raum raum) {
        raeume.add(raum);
        raum.setGebaeude(this); // Ensure bidirectional relationship
    }

    public void removeRaum(Raum raum) {
        raeume.remove(raum);
        raum.setGebaeude(null);
    }

    @Override
    public String toString() {
        return bezeichnung; // Changed to bezeichnung for ComboBox display
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Gebaeude gebaeude = (Gebaeude) o;
        return id == gebaeude.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
