package dtu.project.app.application;

import dtu.project.app.objects.Project;
import dtu.project.app.objects.Task;
import dtu.project.app.objects.User;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectPlanningApp {

    private int uniqueID = 0;
    private User currentUser = null;
    private List<User> users = new ArrayList<User>();
    private List<Project> projects = new ArrayList<Project>();
    private ObservableList<Project> projectsView = FXCollections.observableArrayList();;


    private boolean loggedIn = false;

    public ProjectPlanningApp(){
        users.add(new User("huba"));
        users.add(new User("aha"));
        users.add(new User("ekki"));
        Project project1 = new Project("Test1");
        project1.addTask(new Task("Task1", 1, 1, 2));
        project1.addTask(new Task("Task2", 1, 1, 2));
        project1.addTask(new Task("Task3", 1, 1, 2));
        project1.getTask("Task1").markCompleted();
        project1.getTask("Task2").markCompleted();
        Project project2 = new Project("Test2");
        project2.addTask(new Task("Task1", 1, 1, 2));
        project2.addTask(new Task("Task2", 1, 1, 2));
        project2.addTask(new Task("Task3", 1, 1, 2));
        project2.getTask("Task1").markCompleted();
        project2.setProjectManager("huba");
        projects.add(project1);
        projects.add(project2);
    }
    public boolean isLoggedIn(){
        return loggedIn;
    }

    public void userLogin(String initials){

        //Check for user in database and set to current user if found
        for(User user : users){
            if(user.getInitials().equals(initials)){
                currentUser = user;
                loggedIn = true;
                return;
            }
        }
        loggedIn = false;
    }

    public void userLogout() {
        loggedIn = false;
    }

    public boolean userIsContainedInDatabase(String initials) {
        for (User user: users) {
            if (Objects.equals(user.getInitials(), initials)){
                return true;
            }
        }
        return false;
    }

    public void createNewProject(String projectName){
        Project newProject = new Project(projectName);

        //Take the last 2 digits of the current year and store in variable
        String year = Integer.toString(Year.now().getValue());
        String[] yearArray = year.split("");
        year = yearArray[2] + yearArray[3];

        //Creates a project number for the project consisting of the last 2 numbers of the current year
        //as well as a 5 digit unique id fx. 2300032, 2303943, 2310283
        //The limit for project numbers are 99999 different project id's
        //Can be further developed to reset each year

        String projectID = "";

        if(uniqueID < 10){
            projectID = year + "0000" + uniqueID;
        } else if(uniqueID < 100){
            projectID = year + "000" + uniqueID;
        } else if(uniqueID < 1000){
            projectID = year + "00" + uniqueID;
        } else if(uniqueID < 10000){
            projectID = year + "0" + uniqueID;
        } else if(uniqueID < 100000){
            projectID = year + uniqueID;
        }

        newProject.setProjectId(projectID);
        projects.add(newProject);

        uniqueID++;
    }

    public void addUserToDatabase(String initials){
        users.add(new User(initials));
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public ObservableList<Project> getAllProjectsViewable(){
        projectsView.addAll(projects);
        return projectsView;
    }
}
