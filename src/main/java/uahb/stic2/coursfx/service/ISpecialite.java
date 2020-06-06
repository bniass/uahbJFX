package uahb.stic2.coursfx.service;


import uahb.stic2.coursfx.model.Specialite;

import java.util.List;

public interface ISpecialite {
    public List<Specialite> findAll() throws Exception;
    public List<Specialite> findAllSpecialiteWithService() throws Exception;
    public Specialite findByLibelle(String libelle) throws Exception;
    public Specialite save(Specialite specialite) throws Exception;
    public void update(Specialite specialite) throws Exception;
    public void delete(int specialite_id) throws Exception;
}
