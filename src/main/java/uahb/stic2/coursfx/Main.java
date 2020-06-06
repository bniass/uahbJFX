package uahb.stic2.coursfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uahb.stic2.coursfx.utils.LoadView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        LoadView.showView("COURS FX","FormService.fxml",1);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
