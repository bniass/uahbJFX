package uahb.stic2.coursfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import uahb.stic2.coursfx.model.Service;
import uahb.stic2.coursfx.model.Specialite;
import uahb.stic2.coursfx.service.IService;
import uahb.stic2.coursfx.service.ISpecialite;
import uahb.stic2.coursfx.service.ServiceService;
import uahb.stic2.coursfx.service.SpecialiteService;
import uahb.stic2.coursfx.utils.DatabaseHelper;
import uahb.stic2.coursfx.utils.Utils;

public class ServiceController {

    @FXML
    private TextField serviceTfd;

    @FXML
    private TextField specialiteTfd1;

    @FXML
    private TextField specialiteTfd2;

    @FXML
    void saveHandle(ActionEvent event) throws Exception{
        IService iService = new ServiceService();
        ISpecialite iSpecialite = new SpecialiteService();
        DatabaseHelper db = DatabaseHelper.getInstance();
        try {
            Service s = new Service();
            db.beginTransaction();
            iService.save(s);
            Specialite sp1 = new Specialite();
            sp1.setLibelle(specialiteTfd1.getText());
            sp1.setServive(s);
            iSpecialite.save(sp1);
            Specialite sp2 = new Specialite();
            sp2.setLibelle(specialiteTfd2.getText());
            sp2.setServive(s);
            iSpecialite.save(sp2);
            db.myCommit();
            Utils.showMessage("COURS FX",
                    "Gestion des Service",
                    "Success");
        }catch (Exception e){
            db.myRollback();
            Utils.showMessage("COURS FX",
                    "Gestion des Service",
                    "Erreur :"+e);
        }
    }

}
