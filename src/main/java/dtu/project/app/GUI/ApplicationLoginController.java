package dtu.project.app.GUI;

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

    @FXML
    TextField textFieldInitials;

    public void login(ActionEvent event) throws IOException{

        String username = textFieldInitials.getText();

        //Load projectview.fxml and initialize main controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("projectview.fxml"));
        root = loader.load();
        ApplicationMainController applicationMainController = loader.getController();

        //Check if name typed in textfield exists users for the project planning app
        if (!applicationMainController.successfulLogin(username)){return;}

        //Change scene to projectview.fxml and display username at label
        applicationMainController.displayCurrentUser(username);
        applicationMainController.initializeData();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
