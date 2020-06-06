package uahb.stic2.coursfx.service;



import uahb.stic2.coursfx.model.Service;
import uahb.stic2.coursfx.utils.DatabaseHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServiceService implements IService {
    public List<Service> findAll() throws Exception {
        List<Service> services = new ArrayList<Service>();

        try {
            DatabaseHelper db = DatabaseHelper.getInstance();
            ResultSet rs = db.mySelect("service");

            while (rs.next()){
                Service s = new Service(rs.getInt(1), rs.getString(2));
                services.add(s);
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        }
        return services;
    }

    public Service save(Service service) throws Exception {
        try {
            DatabaseHelper db = DatabaseHelper.getInstance();
            String sql = "INSERT INTO service VALUES(null, ?)";
            Object[] params = {service.getLibelle()};
            db.myPreparedStatement(sql, params);
            db.myExecuteUpdate();
            ResultSet rs = db.getPstmt().getGeneratedKeys();
            if (rs.next()){
                service.setId(rs.getInt(1));
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        }
        return service;
    }
}
