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
import java.util.Objects;

public class ApplicationMainController{

    private final ProjectPlanningApp projectPlanningApp;
    //private ObservableList<Project> projectsView;

    //private List<User> users;
    //private ObservableList<Project> projects;
    //private List<Task> tasks;

    private Project currentProject;

    public ApplicationMainController(){
        this.projectPlanningApp = new ProjectPlanningApp();
    }

    @FXML
    private ListView<Project> listAvailableProjects;

    @FXML
    private ListView<Task> listAvailableTasks;

    @FXML
    private TextField textFieldProjectName;

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
    private Button buttonResign;

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
        if(projectPlanningApp.userIsContainedInDatabase(initials)){
            projectPlanningApp.userLogin(initials);
            return true;
        }
        return false;
    }
    public void initializeData(){

        displayProjects();
    }

    @FXML
    private void displayProjects(){
        listAvailableProjects.getItems().clear();
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
                            displayCurrentProject(newValue);
                            displayTasks(newValue);
                        }
                    }
                });
    }
    @FXML
    private void displayCurrentProject(Project project){
        currentProject = project;
        labelProjectName.setText("Project name: " + project.getName());

        if(project.getIsProjectManagerAssigned()) {
            labelManagerAssigned.setText("Current project manager: " + project.getProjectManager());
            buttonBecomeManager.setVisible(false);
            if(Objects.equals(projectPlanningApp.getCurrentUser().getInitials(), project.getProjectManager())){
                labelManagerAssigned.setText("Current project manager: " + project.getProjectManager() + " (you)");
                buttonResign.setVisible(true);
            } else {
                buttonResign.setVisible(false);
            }
        } else {
            labelManagerAssigned.setText("Current project manager: ");
            buttonBecomeManager.setVisible(true);
            buttonResign.setVisible(false);
        }
        labelTotalWorkers.setText("Total workers assigned: " + project.getWorkerAmount());
        labelTasksCompleted.setText("Tasks completed: " +
                project.getTasksCompleted() +
                " / " +
                project.getTaskAmount());
        double progress = (double) project.getTasksCompleted()/(double) project.getTaskAmount();
        progressTasksCompleted.setProgress(progress);
    }

    public void displayTasks(Project project){
        listAvailableTasks.getItems().clear();
        listAvailableTasks.setItems(project.getAllTasksViewable());
        listAvailableTasks.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> param) {
                ListCell<Task> cell; // ListCell

                cell = new ListCell<Task>() {
                    @Override
                    public void updateItem(Task task, boolean empty) {
                        super.updateItem(task, empty);
                        if (task != null) {
                            setText(task.getTitle());
                        }
                    }
                };
                return cell;
            }
        });

        listAvailableTasks.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Task>() {
                    public void changed(ObservableValue<? extends Task> ov,
                                        Task oldValue, Task newValue) {
                        if(newValue != null) {
                            displayCurrentTask(newValue);
                        }
                    }
                });
    }

    @FXML
    public void displayCurrentTask(Task task){

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

    @FXML
    public void becomeProjectManager(ActionEvent actionevent){
        currentProject.setProjectManager(projectPlanningApp.getCurrentUser().getInitials());
        displayCurrentProject(currentProject);
    }

    @FXML
    public void removeProjectManager(ActionEvent actionevent){
        currentProject.removeProjectManager(projectPlanningApp.getCurrentUser().getInitials());
        displayCurrentProject(currentProject);
    }

    @FXML
    public void createNewProject(ActionEvent actionevent){
        String projectName = textFieldProjectName.getText();
        if(!projectPlanningApp.projectIsContainedInDatabase(projectName)) {
            projectPlanningApp.createNewProject(projectName);
        }
    }
}