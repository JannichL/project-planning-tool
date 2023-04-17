package dtu.project.app;

import java.util.ArrayList;
import java.util.List;

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
            if (user.initials.equals(initials)){
                return true;
            }
        }
        return false;
    }

}
