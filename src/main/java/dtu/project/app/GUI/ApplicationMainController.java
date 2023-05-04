package dtu.project.app.GUI;

import dtu.project.app.application.ProjectPlanningApp;
import dtu.project.app.objects.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

public class ApplicationMainController{

    private ProjectPlanningApp projectPlanningApp;
    private ObservableList<Project> projectsView;

    private List<User> users;
    private ObservableList<Project> projects;
    private List<Task> tasks;


    public ApplicationMainController(){
        this.projectPlanningApp = new ProjectPlanningApp();
    }

    @FXML
    private ListView<Project> listAvailableProjects;

    @FXML
    private ListView<Project> availableTasksList;

    @FXML
    private TextField textFieldInitials;

    @FXML
    private Label labelCurrentUser;
    @FXML
    private Label labelProjectName;
    @FXML
    private Label labelManagerAssigned;
    @FXML
    private Label labelTotalWorkers;
    @FXML
    private Label labelTasksCompleted;
    @FXML
    private ProgressBar progressTasksCompleted;
    @FXML
    private Button buttonBecomeManager;

    @FXML
    private Button buttonLogout;

    @FXML
    private Button buttonLogin;
    @FXML
    private Button testbtn;

    public void displayCurrentUser(String username) {
        labelCurrentUser.setText("Hello " + username);
    }

    public boolean successfulLogin(String initials){
        return projectPlanningApp.userIsContainedInDatabase(initials);
    }
    public void initializeData(){

        listAvailableProjects.setItems(projectPlanningApp.getAllProjectsViewable());

        listAvailableProjects.setCellFactory(new Callback<ListView<Project>, ListCell<Project>>() {
            @Override
            public ListCell<Project> call(ListView<Project> param) {
                ListCell<Project> cell; // ListCell

                cell = new ListCell<Project>() {
                    @Override
                    public void updateItem(Project project, boolean empty) {
                        super.updateItem(project, empty);
                        if (project != null) {
                            setText(project.getName());
                        }
                    }
                };
                return cell;
            }
        });

        listAvailableProjects.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Project>() {
                    public void changed(ObservableValue<? extends Project> ov,
                                        Project oldValue, Project newValue) {
                        if(newValue != null) {
                            labelProjectName.setText("Project name: " + newValue.getName());

                            if(newValue.getIsProjectManagerAssigned()) {
                                labelManagerAssigned.setText("Current project manager: " + newValue.getProjectManager());
                                buttonBecomeManager.setVisible(false);
                            } else {
                                labelManagerAssigned.setText("Current project manager: ");
                                buttonBecomeManager.setVisible(true);
                            }
                            labelTotalWorkers.setText("Total workers assigned: " + Integer.toString(newValue.getWorkerAmount()));
                            labelTasksCompleted.setText("Tasks completed: " +
                                                        Integer.toString(newValue.getTasksCompleted()) +
                                                        " / " +
                                                        Integer.toString(newValue.getTaskAmount()));
                            double progress = (double) newValue.getTasksCompleted()/(double) newValue.getTaskAmount();
                            progressTasksCompleted.setProgress(progress);
                        }

                    }
                });
    }
    @FXML
    public void logout(ActionEvent actionEvent) throws IOException {

        //Load login view and change scene to that
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Project planning tool");
        stage.setScene(scene);
        stage.show();

        projectPlanningApp.userLogout();
    }
    /*
    @FXML
    public void login(ActionEvent actionEvent) throws IOException{
        User u = new User("huba");
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try{
            Parent root = FXMLLoader.load(Main.class.getResource("projectview.fxml"));
            stage.setUserData(projectPlanningApp);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }
    */
    @FXML
    public void changeLabel(ActionEvent actionEvent){
        //Displays username in lower right corner
        labelCurrentUser.setText(projectPlanningApp.getCurrentUser().getInitials());
    }

}