package dtu.project.app.project.acceptance_tests;

import dtu.project.app.application.InvalidOperationException;
import dtu.project.app.application.ProjectPlanningApp;
import dtu.project.app.objects.Project;
import dtu.project.app.objects.Task;
import dtu.project.app.objects.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.*;

public class LogHoursStep {

    private ProjectPlanningApp projectPlanningApp;
    //private Project project;
    private List<Project> projects = new ArrayList<Project>();
    private User user;
    private String projectName;


    public LogHoursStep(ProjectPlanningApp projectPlanningApp) {
        this.projectPlanningApp = projectPlanningApp;
    }

    @Given("there is a User with the initials {string}")
    public void thereIsAUserWithTheInitials(String initials) {
        projectPlanningApp.addUser(initials);
        projectPlanningApp.userLogin(initials);
    }

    @Given("there exists a project with the name {string}")
    public void thereExistsAProjectWithTheName(String string) {
        // Write code here that turns the phrase above into concrete actions
        projectPlanningApp.createNewProject(string);
        projectName = string;
        assertNotNull(projectPlanningApp.getProject(string));
    }
    @Given("the project has a task called {string} with startWeek {int}, endWeek {int}, budgettedHours {int}")
    public void theProjectHasATaskCalledWithStartWeekEndWeekBudgettedHours(String string, Integer int1, Integer int2, Integer int3) throws InvalidOperationException {
        projectPlanningApp.getProject(projectName).addTask(new Task(string, int1, int2, int3));
        assertNotNull(projectPlanningApp.getProject(projectName).getTask(string));
    }
    @When("the User logs {int} hours of work on task {string} in project with the name {string}")
    public void theUserLogsHoursOfWorkOnTaskInProjectWithTheName(Integer int1, String string, String string2) {
        projectPlanningApp.getCurrentUser().addLogHours(string2, string, int1);
    }
    @Then("the system will register {int} hours of work on task {string} in project with the name {string}")
    public void theSystemWillRegisterHoursOfWorkOnTaskInProjectWithTheName(Integer int1, String string, String string2) {
        assertTrue(projectPlanningApp.getCurrentUser().getLoggedHours(string2, string, int1));
    }
}
