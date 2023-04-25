package dtu.project.app.application;

import dtu.project.app.objects.Project;
import dtu.project.app.objects.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectPlanningApp {

    private List<User> users = new ArrayList<User>();
    private List<Project> projects;

    private boolean loggedIn = false;

    public boolean isLoggedIn(){
        return loggedIn;
    }

    public boolean userLogin(String initials){
        loggedIn = initials.equals("huba");
        return loggedIn;

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

}
