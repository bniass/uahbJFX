package uahb.stic2.coursfx.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private TextField specialiteTfd;

    @FXML
    private ComboBox<Service> serviceCbx;

    @FXML
    private TableColumn<Specialite, Integer> idCol;

    @FXML
    private TableColumn<Specialite, String> libelleCol;

    @FXML
    private TableColumn<Specialite, Service> serviceCol;

    @FXML
    private TableView<Specialite> specialiteTbv;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button newBtn;

    ISpecialite iSpecialite;
    IService iService;

    @FXML
    void newHandle(ActionEvent event) {
        activateButton(true);
        specialiteTbv.getSelectionModel().clearSelection();
    }

    @FXML
    void updateHandle(ActionEvent event) {
        if(!verif()){
            return;
        }
        try {
            s.setLibelle(specialiteTfd.getText());
            iSpecialite.update(s);
            Utils.showMessage("COURS FX",
                    "Gestion des spécialité",
                    "La spécialité ["+s+"] est modifié");

            ob.get(pos).setLibelle(s.getLibelle());
            specialiteTbv.refresh();
            specialiteTbv.getSelectionModel().clearSelection();
            saveBtn.setDisable(true);
            updateBtn.setDisable(true);
            deleteBtn.setDisable(true);
        }catch(Exception e){
            Utils.showMessage("COURS FX",
                    "Gestion des spécialité",
                    "Erreur :"+e);
        }

    }

    @FXML
    void deleteHandle(ActionEvent event) {
        boolean ok = Utils.confirmMessage("COURS FX",
                "Gestion des spécialité", "Etes-vous sûr ?");
        if(ok){
            try {
                iSpecialite.delete(s.getId());
                Utils.showMessage("COURS FX",
                        "Gestion des spécialité",
                        "La spécialité ["+s+"] est sauvegardée");
                ob.remove(pos);
                specialiteTbv.refresh();
            }catch(Exception e){
                Utils.showMessage("COURS FX",
                        "Gestion des spécialité",
                        "Erreur :"+e);
            }
        }
    }

    private boolean verif(){
        if(specialiteTfd.getText().trim().isEmpty()){
            Utils.showMessage("COURS FX",
                    "Gestion des spécialité",
                    "Spécialité obligatoire !");
            return false;
        }
        try {
            if(iSpecialite.findByLibelle(specialiteTfd.getText().trim()) != null){
                Utils.showMessage("COURS FX",
                        "Gestion des spécialité",
                        "Cette spécialité exite déja !");
                return false;
            }
        }
        catch(Exception e){

        }

        if(serviceCbx.getSelectionModel().getSelectedItem() == null){
            Utils.showMessage("COURS FX",
                    "Gestion des spécialité",
                    "Service Obligatoire !");
            return false;
        }
        return true;
    }

    @FXML
    void saveHandle(ActionEvent event) {
        if(!verif()){
            return;
        }
        Specialite sp = new Specialite(0, specialiteTfd.getText().trim());
        try {
            Service s = serviceCbx.getValue();
            sp.setServive(s);
            iSpecialite.save(sp);
            Utils.showMessage("COURS FX",
                    "Gestion des spécialité",
                    "La spécialité ["+sp+"] est sauvegardée");
            ob.add(sp);
            specialiteTbv.refresh();
        }catch(Exception e){
            Utils.showMessage("COURS FX",
                    "Gestion des spécialité",
                    "Erreur :"+e);
        }
    }

    ObservableList<Specialite> ob;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iService = new ServiceService();
        try {
            List<Service> services = iService.findAll();
            serviceCbx.setItems(FXCollections.observableArrayList(services));

            idCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
            libelleCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>
                    (cellData.getValue().getLibelle()));
            serviceCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>
                    (cellData.getValue().getServive()));

            iSpecialite = new SpecialiteService();
            List<Specialite> specialites = iSpecialite.findAllSpecialiteWithService();
            ob = FXCollections.observableArrayList(specialites);
            specialiteTbv.setItems(ob);

            saveBtn.setDisable(true);
            updateBtn.setDisable(true);
            deleteBtn.setDisable(true);
            //specialiteTbv.getSelectionModel().
            //setSelectionMode(SelectionMode.MULTIPLE);

            specialiteTbv.setOnMouseClicked((event)->{
                activateButton(false);
                load();
            });
        }
        catch (Exception e){
            Utils.showMessage("COURS FX",
                    "Gestion des spécialité",
                    "Erreur :"+e);
        }



    }

    Specialite s;
    int pos;
    private void load(){
        s = specialiteTbv.getSelectionModel().getSelectedItem();
        if(s != null)
        {
        specialiteTfd.setText(s.getLibelle());
        serviceCbx.setValue(s.getServive());
        pos = specialiteTbv.getSelectionModel().getSelectedIndex();
        }
    }

    private void activateButton(boolean active){
        saveBtn.setDisable(!active);
        updateBtn.setDisable(active);
        deleteBtn.setDisable(active);
    }


}