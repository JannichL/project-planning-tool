package dtu.project.app.application;

import dtu.project.app.objects.Project;
import dtu.project.app.objects.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectPlanningApp {

    private User currentUser = null;
    private List<User> users = new ArrayList<User>();
    private List<Project> projects;

    private boolean loggedIn = false;

    public boolean isLoggedIn(){
        return loggedIn;
    }

    public void userLogin(String initials){
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

    public void addUserToDatabase(String initials){
        users.add(new User(initials));
    }
}
