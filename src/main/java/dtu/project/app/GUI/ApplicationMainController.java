package dtu.project.app.GUI;

import dtu.project.app.application.InvalidOperationException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ApplicationMainController{

    private ProjectPlanningApp projectPlanningApp;

    private Project currentProject;
    private Task currentTask;
    private String userChoice;
    private boolean userSpecificGlobal;
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private ListView<Project> listProjects;

    @FXML
    private ListView<Task> listTasks;

    @FXML
    private TextField textFieldHours;

    @FXML
    private TextField textFieldProjectName;

    @FXML
    private TextField textFieldTaskName;

    @FXML
    private TextField textFieldTaskStartWeek;

    @FXML
    private TextField textFieldTaskEndWeek;

    @FXML
    private TextField textFieldTaskBudgettedHours;

    @FXML
    private ChoiceBox<String> comboViews;
    @FXML
    private ChoiceBox<String> comboUsers;
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
    private Label labelCreateProjectStatus;

    @FXML
    private Label labelAddUser;

    @FXML
    private Label labelUserSelected;

    @FXML
    private Label labelCreateNewTask;

    @FXML
    private Label labelCreateTaskTitle;

    @FXML
    private Label labelCreateTaskStartWeek;

    @FXML
    private Label labelCreateTaskEndWeek;

    @FXML
    private Label labelCreateBudgettedHours;
    @FXML
    private ProgressBar progressTasksCompleted;
    @FXML
    private Button buttonBecomeManager;
    @FXML
    private Button buttonResign;
    @FXML
    private Button buttonLogHours;

    @FXML
    private Button buttonCreateTask;

    @FXML
    private Button buttonUserToTask;

    @FXML
    private Button buttonTaskComplete;
    @FXML
    private GridPane gridPaneLogView;
    @FXML
    private GridPane gridPaneProjectView;

    @FXML
    private GridPane gridPaneManagement;

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
    public void initializeData(ProjectPlanningApp projectPlanningAppData) throws InvalidOperationException{

        if(projectPlanningAppData != null){
            this.projectPlanningApp = projectPlanningAppData;
            //System.out.println("Transferred data!");
        } else {
            this.projectPlanningApp = new ProjectPlanningApp();
            //System.out.println("Created new instance of ProjectPlanningApp!");
        }
        setDropDownView();
        numbersOnly();
    }

    private void setDropDownView(){

        ObservableList<String> choices = FXCollections.observableArrayList();

        String[] choiceArray = {"All projects", "My projects", "Log hours"};

        choices.addAll(Arrays.asList(choiceArray));

        comboViews.setItems(choices);
        comboViews.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if((Integer) number2 >= 0) {
                    String choice = comboViews.getItems().get((Integer) number2);
                    if(Objects.equals(choice, "All projects")){
                        userSpecificGlobal = false;
                        displayProjects(projectPlanningApp.getAllProjectsViewable());
                        showProjectView(true);
                        resetData();
                    } else if (Objects.equals(choice, "My projects")){
                        userSpecificGlobal = true;
                        displayProjects(projectPlanningApp.getMyProjectsViewable());
                        showProjectView(true);
                        resetData();
                    } else if (Objects.equals(choice, "Log hours")){
                        userSpecificGlobal = false;
                        displayProjects(projectPlanningApp.getAllProjectsViewable());
                        showProjectView(false);
                        resetData();
                    }
                }
            }
        });
    }

    private void setDropDownUsers(Task task){

        ObservableList<String> choices = FXCollections.observableArrayList();

        for(User user : projectPlanningApp.getUsers()) {
            if(!task.isWorkerAssigned(user.getInitials())) {
                choices.add(user.getInitials());
            }
        }

        if(choices.isEmpty()){
            choices.add("No users available!");
        }

        comboUsers.setItems(choices);
        comboUsers.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if((Integer) number2 >= 0) {
                    userChoice = comboUsers.getItems().get((Integer) number2);
                }
            }
        });
    }

    @FXML
    private void displayProjects(ObservableList<Project> observableProjectList){

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
                            resetData();
                            displayCurrentProject(newValue);
                            displayTasks(newValue);
                        }
                    }
                });
    }

    @FXML
    private void displayCurrentProject(Project project){
        currentProject = project;
        currentTask = null;
        labelProjectName.setText("Project name: " + project.getName());
        labelLogProjectName.setText("Project name: " + project.getName());

        if(project.getIsProjectManagerAssigned()) {
            labelManagerAssigned.setText("Current project manager: " + project.getProjectManager());
            buttonBecomeManager.setVisible(false);
            showManagement(false);
            if(Objects.equals(projectPlanningApp.getCurrentUser().getInitials(), project.getProjectManager())){
                labelManagerAssigned.setText("Current project manager: " + project.getProjectManager() + " (you)");
                buttonResign.setVisible(true);
                showManagement(true);
            } else {
                buttonResign.setVisible(false);
                showManagement(false);
            }
        } else {
            showManagement(true);
            labelManagerAssigned.setText("Current project manager: ");
            buttonBecomeManager.setVisible(true);
            buttonResign.setVisible(false);
        }
        updateTaskCompletion();
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
        textFieldHours.setText("");

        labelCreateProjectStatus.setVisible(false);
    }

    public void displayTasks(Project project){
        listTasks.getItems().clear();
        listTasks.getSelectionModel().clearSelection();
        if(userSpecificGlobal){
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
        updateTaskCompletion();
        setDropDownUsers(task);
        String userInitials = projectPlanningApp.getCurrentUser().getInitials();
        buttonTaskComplete.setVisible(task.isWorkerAssigned(userInitials) || Objects.equals(currentProject.getProjectManager(), userInitials));
        labelLogSuccess.setVisible(false);
        textFieldHours.setText("");
        labelTaskTitle.setText("Title: " + task.getTitle());
        labelTaskStartWeek.setText("Start week: " + task.getStartWeek());
        labelTaskEndWeek.setText("End week: " + task.getEndWeek());
        labelTaskBudgettedHours.setText("Budgetted hours: " + task.getBudgetedHours());
        String completed;
        if(task.getIsCompleted()){
            completed = "Complete";
            buttonTaskComplete.setText("Mark as incomplete");
        } else {
            completed = "Incomplete";
            buttonTaskComplete.setText("Mark as complete");
        }
        labelTaskCompleted.setText("Completion status : " + completed);

        labelLogTaskName.setText("Task name: " + task.getTitle());
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {

        //Load login view and change scene to that
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        root = loader.load();
        ApplicationLoginController applicationLoginController = loader.getController();
        applicationLoginController.transferData(projectPlanningApp);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 400);
        stage.setScene(scene);
        stage.show();
        /*
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Project planning tool");
        stage.setScene(scene);
        stage.show();
        */


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
        if(Objects.equals(projectName, "")){
            labelCreateProjectStatus.setVisible(true);
            labelCreateProjectStatus.setText("Please enter a name for the project!");
            return;
        }

        if(projectPlanningApp.projectIsContainedInDatabase(projectName)){
            labelCreateProjectStatus.setVisible(true);
            labelCreateProjectStatus.setText("Project with that name already exists!");
            return;
        }

        projectPlanningApp.createNewProject(projectName);

        if(projectPlanningApp.projectIsContainedInDatabase(projectName)){
            userSpecificGlobal = false;
            displayProjects(projectPlanningApp.getAllProjectsViewable());
            textFieldProjectName.setText("");
            labelCreateProjectStatus.setVisible(true);
            labelCreateProjectStatus.setText("Project created successfully");
        } else {
            labelCreateProjectStatus.setVisible(true);
            labelCreateProjectStatus.setText("Something went wrong! try again later");
        }
    }

    @FXML
    public void showProjectView(Boolean state){
        gridPaneProjectView.setVisible(state);
        gridPaneLogView.setVisible(!state);
    }

    public void logHours(){

        if(currentTask == null){
            labelLogSuccess.setVisible(true);
            labelLogSuccess.setText("Please choose a task!");
            return;
        }

        if(Objects.equals(textFieldHours.getText(), "")){
            labelLogSuccess.setVisible(true);
            labelLogSuccess.setText("Please input correct amount of hours!");
            return;
        }

        if(currentTask != null && currentProject != null && !Objects.equals(textFieldHours.getText(), "")){
            projectPlanningApp.getCurrentUser().addLogHours(currentProject.getName(), currentTask.getTitle(), Integer.parseInt(textFieldHours.getText()));
            labelLogSuccess.setVisible(true);
            if(projectPlanningApp.getCurrentUser().getLoggedHours(currentProject.getName(), currentTask.getTitle(), Integer.parseInt(textFieldHours.getText()))){
                labelLogSuccess.setText("Hours logged successfully!");
            } else {
                labelLogSuccess.setText("Something went wrong! Try again later");
            }
            textFieldHours.setText("");
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

        textFieldTaskStartWeek.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textFieldTaskStartWeek.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        labelCreateTaskEndWeek.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    labelCreateTaskEndWeek.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        labelCreateBudgettedHours.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    labelCreateBudgettedHours.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    public void clearProjectName(){
        labelCreateProjectStatus.setVisible(false);
    }

    @FXML
    public void completeTask(ActionEvent actionEvent){
        if(!currentTask.getIsCompleted()) {
            currentTask.complete();
        } else {
            currentTask.incomplete();
        }
        updateTaskCompletion();
        displayCurrentTask(currentTask);
    }

    @FXML
    public void updateTaskCompletion(){
        labelTasksCompleted.setText("Tasks completed: " +
                currentProject.getTasksCompleted() +
                " / " +
                currentProject.getTaskAmount());
        double progress = (double) currentProject.getTasksCompleted()/(double) currentProject.getTaskAmount();
        progressTasksCompleted.setProgress(progress);
    }

    public void addUserToTask(ActionEvent actionEvent){
        if(projectPlanningApp.userIsContainedInDatabase(userChoice)) {
            currentTask.assignWorker(projectPlanningApp.getUser(userChoice));
            setDropDownUsers(currentTask);
        }
    }

    public void createTask(ActionEvent actionEvent) throws InvalidOperationException{
        if(Objects.equals(textFieldTaskName.getText(), "")){
            labelCreateNewTask.setText("Please enter task name!");
            return;
        }

        if(Objects.equals(textFieldTaskStartWeek.getText(), "")){
            labelCreateNewTask.setText("Please enter start week!");
            return;
        }

        if(Objects.equals(textFieldTaskEndWeek.getText(), "")){
            labelCreateNewTask.setText("Please enter end week!");
            return;
        }

        if(Objects.equals(textFieldTaskBudgettedHours.getText(), "")){
            labelCreateNewTask.setText("Please enter budgetted hours!");
            return;
        }

        if(Integer.parseInt(textFieldTaskStartWeek.getText()) > Integer.parseInt(textFieldTaskEndWeek.getText())){
            labelCreateNewTask.setText("End week must be greater than start week!");
            return;
        }

            currentProject.addTask(new Task(textFieldTaskName.getText(),
                    Integer.parseInt(textFieldTaskStartWeek.getText()),
                    Integer.parseInt(textFieldTaskEndWeek.getText()),
                    Integer.parseInt(textFieldTaskBudgettedHours.getText())));
            labelCreateNewTask.setText("Task created successfully!");


        displayTasks(currentProject);
    }

    public void showManagement(Boolean state){

        gridPaneManagement.setVisible(state);
        /*
        labelAddUser.setVisible(!state);
        labelUserSelected.setVisible(state);
        labelCreateNewTask.setVisible(state);
        labelCreateTaskTitle.setVisible(state);
        labelCreateTaskStartWeek.setVisible(state);
        labelCreateTaskEndWeek.setVisible(state);
        labelCreateBudgettedHours.setVisible(state);
        comboUsers.setVisible(state);
        textFieldTaskName.setVisible(state);
        textFieldTaskStartWeek.setVisible(state);
        textFieldTaskEndWeek.setVisible(state);
        textFieldTaskBudgettedHours.setVisible(state);
        buttonCreateTask.setVisible(state);
        buttonUserToTask.setVisible(state);
        */

    }
}
