package uahb.stic2.coursfx.service;

import uahb.stic2.coursfx.model.Service;
import uahb.stic2.coursfx.model.Specialite;
import uahb.stic2.coursfx.utils.DatabaseHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SpecialiteService implements ISpecialite {
    public List<Specialite> findAll() {
        List<Specialite> specialites = new ArrayList<Specialite>();
        try {
            DatabaseHelper db = new DatabaseHelper();
            ResultSet rs = db.mySelect("specialite");
            while (rs.next()){
                Specialite s = new Specialite(rs.getInt(1), rs.getString(2));
                specialites.add(s);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return specialites;
    }

    @Override
    public List<Specialite> findAllSpecialiteWithService() {
        List<Specialite> specialites = new ArrayList<Specialite>();
        try {
            DatabaseHelper db = new DatabaseHelper();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return specialites;
    }

    public Specialite findByLibelle(String libelle) {
        Specialite specialite = null;
        try {
            DatabaseHelper db = new DatabaseHelper();
            String sql = "SELECT * FROM specialite WHERE libelle = ?";
            Object[] params = {libelle};
            db.myPreparedStatement(sql, params);
            ResultSet rs = db.myExecuteQuery();
            if (rs.next()){
                specialite = new Specialite(rs.getInt(1), rs.getString(2));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return specialite;
    }

    public Specialite save(Specialite specialite) {
        try {
            DatabaseHelper db = new DatabaseHelper();
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
            e.printStackTrace();
        }
        return specialite;
    }

    public void update(Specialite specialite) {
        try {
            DatabaseHelper db = new DatabaseHelper();
            String sql = "UPDATE specialite set libelle = ? WHERE id = ?";
            Object[] params = {specialite.getLibelle(), specialite.getId()};
            db.myPreparedStatement(sql, params);
            db.myExecuteUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int specialite_id) {
        try {
            DatabaseHelper db = new DatabaseHelper();
            String sql = "DELETE FROM specialite WHERE id = ?";
            Object[] params = {specialite_id};
            db.myPreparedStatement(sql, params);
            db.myExecuteUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
