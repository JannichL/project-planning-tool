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
    private ObservableList<Project> projectsView = FXCollections.observableArrayList();


    private boolean loggedIn = false;

    public ProjectPlanningApp() throws InvalidOperationException{

        setTestData();
    }

    private void setTestData() throws InvalidOperationException{
        //Test data for UI testing
        users.add(new User("huba"));
        users.add(new User("ekki"));
        users.add(new User("aha"));

        createNewProject("Software planning tool");
        createNewProject("Calculator");
        createNewProject("Corona Dashboard");

        int i = 1;
        for(Project project : projects){

            if(i == 1) {
                project.setProjectManager("ekki");
                project.addTask(new Task("Implementation", 100, 5, 10));
                project.addTask(new Task("Class diagrams", 100, 7, 12));
                project.addTask(new Task("Animations", 100, 9, 14));
            }

            if(i == 2) {
                project.setProjectManager("huba");
                project.addTask(new Task("Implementation", 100, 5, 10));
                project.addTask(new Task("Class diagrams", 100, 7, 12));
                project.addTask(new Task("Animations", 100, 9, 14));
            }

            if(i == 3) {
                project.setProjectManager("aha");
                project.addTask(new Task("Implementation", 100, 5, 10));
                project.addTask(new Task("Class diagrams", 100, 7, 12));
                project.addTask(new Task("Animations", 100, 9, 14));
            }

            int j = 1;
            for(Task task : project.getTasks()){
                if(j == 1) {
                    task.assignWorker(getUser("huba"));
                }

                if(j == 2) {
                    task.assignWorker(getUser("aha"));
                }

                if(j == 3) {
                    task.assignWorker(getUser("ekki"));
                }
                j++;
            }
            i++;
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

    public void addUser(String initials){
        users.add(new User(initials));
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

    public Project getProject(String projectName){
        for(Project project : projects){
            if(Objects.equals(project.getName(), projectName)){
                return project;
            }
        }
        return null;
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
                //System.out.println(task.getAssignedWorkers());
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
