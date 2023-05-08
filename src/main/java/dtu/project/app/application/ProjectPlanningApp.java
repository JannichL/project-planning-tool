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

    private boolean loggedIn = false;

    public ProjectPlanningApp(){

        //Test data for UI testing
        users.add(new User("huba"));
        users.add(new User("aha"));
        users.add(new User("ekki"));
        for(int i = 0; i < 10; i++){
            createNewProject("Test" + i);
        }
        for(Project project : projects){
            for(int j = 0; j < 10; j++){
                project.addTask(new Task("Task" + j, j*10, j, j+1));
                if( j % 2 == 0) {
                    project.getTask("Task" + j).complete();
                    project.getTask("Task" + j).assignWorker(users.get(0));
                }
            }
            project.setProjectManager("huba");
        }
        createNewProject("Ekki personal project");

        for(Project project : projects) {
            if(projectIsContainedInDatabase("Ekki personal project")){
                project.setProjectManager("ekki");
                project.addTask(new Task("Huba's personal task", 1, 10, 100));
            }
        }
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

    public List<User> getUsers(){
        return users;
    }

    public User getUser(String initials){
        for(User user : users){
            if(Objects.equals(initials, user.getInitials())){
                return user;
            }
        }
        return null;
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

    public boolean projectIsContainedInDatabase(String title) {
        for (Project project: projects) {
            if (Objects.equals(project.getName(), title)){
                return true;
            }
        }
        return false;
    }

    public List<String> getProjectNames() {
        List<String> projectNames = new ArrayList<>();
        for (Project project : projects) {
            projectNames.add(project.getName());
        }
        return projectNames;
    }


    public void addUserToDatabase(String initials){
        users.add(new User(initials));
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public ObservableList<Project> getAllProjectsViewable(){
        ObservableList<Project> projectsView = FXCollections.observableArrayList();
        projectsView.addAll(projects);
        return projectsView;
    }

    public ObservableList<Project> getMyProjectsViewable(){
        ObservableList<Project> projectsView = FXCollections.observableArrayList();
        boolean projectProcessed;
        for(Project project : projects){
            projectProcessed = false;
            if(Objects.equals(currentUser.getInitials(), project.getProjectManager())){
                projectsView.add(project);
                projectProcessed = true;
            }
            nextProject:
            for(Task task : project.getTasks()){
                System.out.println(task.getAssignedWorkers());
                for(User user : task.getAssignedWorkers()){
                    if(Objects.equals(user.getInitials(), currentUser.getInitials()) && !projectProcessed){
                        projectsView.add(project);
                        break nextProject;
                    }
                }
            }
        }
        return projectsView;
    }
}
