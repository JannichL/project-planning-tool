package dtu.project.app.GUI;

import dtu.project.app.application.ProjectPlanningApp;
import dtu.project.app.objects.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class ApplicationMainController{

    private final ProjectPlanningApp projectPlanningApp;

    private Project currentProject;
    private Task currentTask;

    public ApplicationMainController(){
        this.projectPlanningApp = new ProjectPlanningApp();
    }

    @FXML
    private ListView<Project> listProjects;

    @FXML
    private ListView<Task> listTasks;

    @FXML
    private TextField textFieldHours;

    @FXML
    private TextField textFieldProjectName;

    @FXML
    private ChoiceBox<String> dropDown;
    @FXML
    private Label labelCurrentUser;
    @FXML
    private Label labelProjectName;
    @FXML
    private Label labelManagerAssigned;
    @FXML
    private Label labelTotalWorkers;
    @FXML
    private Label labelTask;
    @FXML
    private Label labelTasksCompleted;
    @FXML
    private Label labelTaskTitle;
    @FXML
    private Label labelTaskStartWeek;
    @FXML
    private Label labelTaskEndWeek;
    @FXML
    private Label labelTaskBudgettedHours;
    @FXML
    private Label labelTaskCompleted;
    @FXML
    private Label labelLogProjectName;
    @FXML
    private Label labelLogTaskName;
    @FXML
    private Label labelLogSuccess;
    @FXML
    private ProgressBar progressTasksCompleted;
    @FXML
    private Button buttonBecomeManager;
    @FXML
    private Button buttonResign;
    @FXML
    private Button buttonLogHours;
    @FXML
    private GridPane gridPaneLogView;
    @FXML
    private GridPane gridPaneProjectView;

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

        setDropDown();
        numbersOnly();
    }

    private void setDropDown(){

        ObservableList<String> choices = FXCollections.observableArrayList();

        String[] choiceArray = {"All projects", "My projects", "Log hours"};

        choices.addAll(Arrays.asList(choiceArray));

        dropDown.setItems(choices);
        dropDown.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if((Integer) number2 >= 0) {
                    String choice = dropDown.getItems().get((Integer) number2);
                    if(Objects.equals(choice, "All projects")){
                        displayProjects(projectPlanningApp.getAllProjectsViewable(), false);
                        showProjectView(true);
                        resetData();
                    } else if (Objects.equals(choice, "My projects")){
                        displayProjects(projectPlanningApp.getMyProjectsViewable(), true);
                        showProjectView(true);
                        resetData();
                    } else if (Objects.equals(choice, "Log hours")){
                        displayProjects(projectPlanningApp.getAllProjectsViewable(), false);
                        showProjectView(false);
                        resetData();
                    }
                }
            }
        });
    }

    @FXML
    private void displayProjects(ObservableList<Project> observableProjectList, Boolean userSpecific){

        listProjects.getItems().clear();
        listProjects.getSelectionModel().clearSelection();
        listProjects.setItems(observableProjectList);

        listProjects.setCellFactory(new Callback<ListView<Project>, ListCell<Project>>() {
            @Override
            public ListCell<Project> call(ListView<Project> param) {
                ListCell<Project> cell; // ListCell

                cell = new ListCell<Project>() {
                    @Override
                    public void updateItem(Project project, boolean empty) {
                        super.updateItem(project, empty);
                        if(empty || project == null) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            setText(project.getProjectId() + " - " + project.getName());
                        }
                    }
                };
                return cell;
            }
        });

        listProjects.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Project>() {
                    public void changed(ObservableValue<? extends Project> ov,
                                        Project oldValue, Project newValue) {
                        if(newValue != null) {
                            displayCurrentProject(newValue);
                            displayTasks(newValue, userSpecific);
                        }
                    }
                });
    }

    @FXML
    private void displayCurrentProject(Project project){
        currentProject = project;
        labelProjectName.setText("Project name: " + project.getName());
        labelLogProjectName.setText("Project name: " + project.getName());

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

    @FXML
    private void resetData(){
        currentProject = null;
        currentTask = null;
        labelProjectName.setText("Project name: ");
        labelManagerAssigned.setText("Current project manager: ");
        buttonBecomeManager.setVisible(false);
        buttonResign.setVisible(false);
        labelTotalWorkers.setText("Total workers assigned: ");
        labelTasksCompleted.setText("Tasks completed: ");
        double progress = 0;
        progressTasksCompleted.setProgress(progress);

        labelLogProjectName.setText("Project name: ");
        labelLogTaskName.setText("Task name: ");
        labelLogSuccess.setVisible(false);
    }

    public void displayTasks(Project project, Boolean userSpecific){
        listTasks.getItems().clear();
        listTasks.getSelectionModel().clearSelection();
        if(userSpecific){
            listTasks.setItems(project.getMyTasksViewable(projectPlanningApp.getCurrentUser()));
        } else {
            listTasks.setItems(project.getAllTasksViewable());
        }
        listTasks.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> param) {
                ListCell<Task> cell; // ListCell

                cell = new ListCell<Task>() {
                    @Override
                    public void updateItem(Task task, boolean empty) {
                        super.updateItem(task, empty);
                        if(empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            setText(task.getTitle());
                        }
                    }
                };
                return cell;
            }
        });

        listTasks.getSelectionModel().selectedItemProperty().addListener(
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
        currentTask = task;
        labelTaskTitle.setText("Title: " + task.getTitle());
        labelTaskStartWeek.setText("Start week: " + task.getStartWeek());
        labelTaskEndWeek.setText("End week: " + task.getEndWeek());
        labelTaskBudgettedHours.setText("Budgetted hours: " + task.getBudgetedHours());
        String completed;
        if(task.getIsCompleted()){
            completed = "Complete";
        } else {
            completed = "Incomplete";
        }
        labelTaskCompleted.setText("Completion status : " + completed);

        labelLogTaskName.setText("Task name: " + task.getTitle());
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
            displayProjects(projectPlanningApp.getAllProjectsViewable(), false);
        }
    }

    @FXML
    public void showProjectView(Boolean state){
        gridPaneProjectView.setVisible(state);
        gridPaneLogView.setVisible(!state);
    }

    public void logHours(){
        if(currentTask != null && currentProject != null && textFieldHours.getText() != null){
            //projectPlanningApp.getCurrentUser().logHours(currentProject.getName(), currentTask.getTitle(), textFieldHours.getText());
        }
    }
    @FXML
    public void numbersOnly(){
        // https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
        // force the field to be numeric only
        textFieldHours.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textFieldHours.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    /*
    @FXML
    public void showProjectView(Boolean state){
        labelProjectName.setVisible(state);
        labelManagerAssigned.setVisible(state);
        buttonBecomeManager.setVisible(state);
        buttonResign.setVisible(state);
        labelTotalWorkers.setVisible(state);
        labelTasksCompleted.setVisible(state);
        progressTasksCompleted.setVisible(!state);
        labelTask.setVisible(state);
        labelTaskTitle.setVisible(state);
        labelTaskStartWeek.setVisible(state);
        labelTaskEndWeek.setVisible(state);
        labelTaskBudgettedHours.setVisible(state);
        labelTaskCompleted.setVisible(state);
    }
    */

}