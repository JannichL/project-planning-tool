package dtu.project.app.GUI;

import dtu.project.app.application.ProjectPlanningApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Main extends Application {

    //private ProjectPlanningApp projectPlanningApp;
    @Override
    public void start(Stage stage){

        try{
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
       // this.projectPlanningApp = new ProjectPlanningApp();
       // ApplicationMainController.initModel
        //FXMLLoader login = new FXMLLoader(Main.class.getResource("login.fxml"));
        //FXMLLoader projectView = new FXMLLoader(Main.class.getResource("login.fxml"));

        //Scene scene = new Scene(login.load(), 600, 400);
       // ApplicationMainController applicationMainController = ApplicationMainController.getController();
        //ApplicationMainController.initProjectPlanningApp
        //stage.setTitle("Project planning tool");
        //stage.setScene(scene);
        //stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}