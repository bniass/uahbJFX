package uahb.stic2.coursfx.service;


import uahb.stic2.coursfx.model.Specialite;

import java.util.List;

public interface ISpecialite {
    public List<Specialite> findAll();
    public Specialite findByLibelle(String libelle);
    public Specialite save(Specialite specialite);
    public void update(Specialite specialite);
    public void delete(int specialite_id);
}
