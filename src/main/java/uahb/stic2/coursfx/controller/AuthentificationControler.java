package uahb.stic2.coursfx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import uahb.stic2.coursfx.model.Service;
import uahb.stic2.coursfx.model.Specialite;
import uahb.stic2.coursfx.service.IService;
import uahb.stic2.coursfx.service.ISpecialite;
import uahb.stic2.coursfx.service.ServiceService;
import uahb.stic2.coursfx.service.SpecialiteService;
import uahb.stic2.coursfx.utils.Utils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AuthentificationControler implements Initializable {

    @FXML
    private TextField serviceTfd;

    @FXML
    private ComboBox<Service> serviceCbx;

    ISpecialite iSpecialite;
    IService iService;
    @FXML
    void saveHandle(ActionEvent event) {
        if(serviceTfd.getText().trim().isEmpty()){
            Utils.showMessage("COURS FX",
                    "Gestion des spécialité",
                    "Spécialité obligatoire !");
            return;
        }

        if(iSpecialite.findByLibelle(serviceTfd.getText().trim()) != null){
            Utils.showMessage("COURS FX",
                    "Gestion des spécialité",
                    "Cette spécialité exite déja !");
            return;
        }
        Specialite sp = new Specialite(0, serviceTfd.getText().trim());
        try {
            iSpecialite = new SpecialiteService();
            Service s = serviceCbx.getValue();
            sp.setServive(s);
            iSpecialite.save(sp);
            Utils.showMessage("COURS FX",
                    "Gestion des spécialité",
                    "La spécialité ["+sp+"] est sauvegardée");
        }catch(Exception e){
            Utils.showMessage("COURS FX",
                    "Gestion des spécialité",
                    "Erreur :"+e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iService = new ServiceService();
        List<Service> services = iService.findAll();
        serviceCbx.setItems(FXCollections.observableArrayList(services));
    }
}