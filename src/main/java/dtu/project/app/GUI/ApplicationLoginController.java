package dtu.project.app.GUI;

import dtu.project.app.application.InvalidOperationException;
import dtu.project.app.application.ProjectPlanningApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationLoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private ProjectPlanningApp projectPlanningApp;

    @FXML
    TextField textFieldInitials;

    public void login(ActionEvent event) throws IOException, InvalidOperationException {

        String username = textFieldInitials.getText();

        //Load projectview.fxml and initialize main controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("projectview.fxml"));
        root = loader.load();
        ApplicationMainController applicationMainController = loader.getController();
        applicationMainController.initializeData(projectPlanningApp);
        //Check if name typed in textfield exists users for the project planning app
        if (!applicationMainController.successfulLogin(username)){return;}

        //Change scene to projectview.fxml and display username at label
        applicationMainController.displayCurrentUser(username);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 400);
        stage.setScene(scene);
        stage.show();
    }

    public void transferData(ProjectPlanningApp projectPlanningApp){
        this.projectPlanningApp = projectPlanningApp;
        //System.out.println("Data received successfully from main controller");
    }
}
