package dtu.project.app;

public class ProjectPlanningApp {

    private boolean loggedIn = false;

    public boolean isLoggedIn(){
        return loggedIn;
    }

    public boolean userLogin(String initials){
        loggedIn = initials.equals("huba");
        return loggedIn;

    }

}
