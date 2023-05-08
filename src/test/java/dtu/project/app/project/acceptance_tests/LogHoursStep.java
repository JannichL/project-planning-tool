package dtu.project.app.project.acceptance_tests;

import dtu.project.app.application.ProjectPlanningApp;
import dtu.project.app.objects.Project;
import dtu.project.app.objects.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LogHoursStep {

    private ProjectPlanningApp projectPlanningApp;
    //private Project project;
    private List<Project> projects = new ArrayList<Project>();
    private User user;


    public LogHoursStep(ProjectPlanningApp projectPlanningApp) {
        this.projectPlanningApp = projectPlanningApp;
    }

    @Given("that there are projects available")
    public void thatThereAreProjectsAvailable() {
        assertTrue(projectPlanningApp.getProjectNames().isEmpty());
    }

    @Given("there is a User with the initials {string}")
    public void thereIsAUserWithTheInitials(String initials) {
        projectPlanningApp.addUser(initials);
        projectPlanningApp.userLogin(initials);
    }

    @When("the User logs {int} hours of work on a project with the ID {string} on task {string}")
    public void theUserLogsHoursOfWorkOnAProjectWithTheIDOnTask(Integer Hours, String ID, String Task) {
        projectPlanningApp.getCurrentUser().addLogHours(ID, Task, Hours);
    }

    @Then("the system will register their logged hours {int} and project ID {string} and Task {string}")
    public void theSystemWillRegisterTheirLoggedHoursAndProjectIDAndTask(Integer Hours, String ID, String Task) {
        assertTrue(projectPlanningApp.getCurrentUser().getLoggedHours(ID, Task, Hours));
    }
}
