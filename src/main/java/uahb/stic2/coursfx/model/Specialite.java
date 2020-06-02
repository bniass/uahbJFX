package uahb.stic2.coursfx.model;

public class Specialite {
    private int id;
    private String libelle;
    private Service servive;

    public Specialite(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }
    public Specialite() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Service getServive() {
        return servive;
    }

    public void setServive(Service servive) {
        this.servive = servive;
    }

    @Override
    public String toString() {
        return "["+id+" / "+libelle+"]";
    }
}
