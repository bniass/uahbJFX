package uahb.stic2.coursfx.service;

import uahb.stic2.coursfx.model.Service;
import uahb.stic2.coursfx.model.Specialite;
import uahb.stic2.coursfx.utils.DatabaseHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SpecialiteService implements ISpecialite {
    public List<Specialite> findAll() throws Exception{
        List<Specialite> specialites = new ArrayList<Specialite>();
        try {
            DatabaseHelper db = DatabaseHelper.getInstance();
            ResultSet rs = db.mySelect("specialite");
            while (rs.next()){
                Specialite s = new Specialite(rs.getInt(1), rs.getString(2));
                specialites.add(s);
            }
            rs.close();
            return specialites;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Specialite> findAllSpecialiteWithService() throws Exception{
        List<Specialite> specialites = new ArrayList<Specialite>();
        try {
            DatabaseHelper db = DatabaseHelper.getInstance();
            String sql = "SELECT * FROM specialite sp, service sr "
                    +"WHERE sp.service_id = sr.id";
            Object[] params = {};
            db.myPreparedStatement(sql, params);
            ResultSet rs = db.myExecuteQuery();
            while (rs.next()){
                Specialite sp = new Specialite(rs.getInt(1), rs.getString(2));
                Service s = new Service(rs.getInt(4), rs.getString(5));
                sp.setServive(s);
                specialites.add(sp);
            }
            rs.close();
            return specialites;
        } catch (Exception e) {
            throw e;
        }
    }

    public Specialite findByLibelle(String libelle) throws Exception{
        Specialite specialite = null;
        try {
            DatabaseHelper db = DatabaseHelper.getInstance();
            String sql = "SELECT * FROM specialite WHERE libelle = ?";
            Object[] params = {libelle};
            db.myPreparedStatement(sql, params);
            ResultSet rs = db.myExecuteQuery();
            if (rs.next()){
                specialite = new Specialite(rs.getInt(1), rs.getString(2));
            }
            rs.close();
            return specialite;
        } catch (Exception e) {
            throw e;
        }
    }

    public Specialite save(Specialite specialite) throws Exception {
        try {
            DatabaseHelper db = DatabaseHelper.getInstance();
            String sql = "INSERT INTO specialite VALUES(null, ?, ?)";
            Object[] params = {specialite.getLibelle(), specialite.getServive().getId()};
            db.myPreparedStatement(sql, params);
            db.myExecuteUpdate();
            ResultSet rs = db.getPstmt().getGeneratedKeys();
            if (rs.next()){
                specialite.setId(rs.getInt(1));
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        }
        return specialite;
    }

    public void update(Specialite specialite) throws Exception {
        try {
            DatabaseHelper db = DatabaseHelper.getInstance();
            String sql = "UPDATE specialite set libelle = ? WHERE id = ?";
            Object[] params = {specialite.getLibelle(), specialite.getId()};
            db.myPreparedStatement(sql, params);
            db.myExecuteUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    public void delete(int specialite_id) throws Exception{
        try {
            DatabaseHelper db = DatabaseHelper.getInstance();
            String sql = "DELETE FROM specialite WHERE id = ?";
            Object[] params = {specialite_id};
            db.myPreparedStatement(sql, params);
            db.myExecuteUpdate();
        } catch (Exception e) {
            throw e;
        }
    }
}
