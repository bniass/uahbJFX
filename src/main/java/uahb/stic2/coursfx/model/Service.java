package uahb.stic2.coursfx.model;

import java.util.List;

public class Service {
    private int id;
    private String libelle;
    private List<Specialite> specialites;

    public Service(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }
    public Service() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Specialite> getSpecialites() {
        return specialites;
    }

    public void setSpecialites(List<Specialite> specialites) {
        this.specialites = specialites;
    }

    public int getId() {
        return id;
    }

    public void setiD(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    @Override
    public String toString() {
        return libelle;
    }
}
