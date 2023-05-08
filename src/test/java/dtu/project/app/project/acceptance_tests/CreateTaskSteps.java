package dtu.project.app.project.acceptance_tests;

import dtu.project.app.application.InvalidOperationException;
import dtu.project.app.application.ProjectPlanningApp;
import dtu.project.app.objects.Project;
import dtu.project.app.objects.Task;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateTaskSteps {

    private ProjectPlanningApp projectPlanningApp;
    private Project project;
    private Task task;
    private ErrorMessageHolder errorMessage;
    public CreateTaskSteps(ProjectPlanningApp projectPlanningApp){
        this.projectPlanningApp = projectPlanningApp;
    }

    @Given("there is a project called {string}")
    public void thereIsAProjectCalled(String string) {
        projectPlanningApp.createNewProject(string);
        project = projectPlanningApp.getProject(string);
        assertTrue(projectPlanningApp.projectIsContainedInDatabase(string));
    }

    @Given("user {string} and {string} exists in the database")
    public void theseUsersExistInDatabase(String user1, String user2) {
        projectPlanningApp.addUser(user1);
        projectPlanningApp.addUser(user2);
    }

    @Given("the current user is {string}")
    public void theCurrentUserIs(String string) {
        projectPlanningApp.userLogin(string);
        assertEquals(projectPlanningApp.getCurrentUser().getInitials(), string);
    }
    @Given("there is no ProjectManager elected")
    public void NoProjectManager() {
        assertFalse(project.getIsProjectManagerAssigned());
    }

    @Given("the User creates a task with title {string}, budgetedHours {int}, startWeek {int}, and endWeek {int}")
    public void TaskExists(String title, Integer budgetedHours, Integer startWeek, Integer endWeek) {
        task = (new Task(title, startWeek, endWeek, budgetedHours));
        assertNotNull(task);
    }

    @Given("the task is not contained in the project")
    public void TaskNotInProject() {
        assertNull(project.getTask(task.getTitle()));
    }

    @When("the task is added to the project")
    public void AddTask(){
        /*
        if(project.getIsProjectManagerAssigned() && !Objects.equals(projectPlanningApp.getCurrentUser().getInitials(), project.getProjectManager())){
            InvalidOperationException e = new InvalidOperationException("Only the project manager can add tasks!");
            errorMessage.setErrorMessage(e.getMessage());
            return;
        }

         */

        try {
            System.out.println("Trying to add project!");
            project.addTask(task);
        } catch (InvalidOperationException e) {
            errorMessage.setErrorMessage(e.getMessage());
            System.out.println("Caught error!");
        }
    }

    @Then("the task with title {string}, budgetedHours {int}, startWeek {int}, and endWeek {int} is contained in the project")
    public void TaskExistsInProject(String title, Integer budgetedHours, Integer startWeek, Integer endWeek) {
        Task task = project.getTask(title);
        assertEquals(title, task.getTitle());
    }

    @Given("there is a ProjectManager elected")
    public void ProjectManagerExists() {
        project.setProjectManager("huba");
        assertTrue(project.getIsProjectManagerAssigned());
    }

    @Given("the User is the ProjectManager")
    public void UserIsProjectManager() {
        assertEquals(project.getProjectManager(), projectPlanningApp.getCurrentUser().getInitials());
    }

    @Given("the User is not the ProjectManager")
    public void UserIsNotProjectManager() {
        project.setProjectManager("ekki");
        assertNotEquals(project.getProjectManager(), projectPlanningApp.getCurrentUser().getInitials());
    }

    @Given("the user has access to creating tasks")
    public void theUserHasAccessToCreatingTasks() {
        assertTrue(Objects.equals(project.getProjectManager(), projectPlanningApp.getCurrentUser().getInitials()) || !project.getIsProjectManagerAssigned());
    }
    @Given("the task is already contained in the project")
    public void theTaskIsContainedInTheProject() throws InvalidOperationException{
        project.addTask(task);
        assertSame(task.getTitle(), project.getTask(task.getTitle()).getTitle());
    }
    /*
    @When("the User creates a task with title {string}, budgetedHours {int}, startWeek {int}, and endWeek {int}")
    public void theUserCreatesATaskWithTitleBudgetedHoursStartWeekAndEndWeek(String title, Integer budgetedHours, Integer startWeek, Integer endWeek) {
        task = (new Task(title, startWeek, endWeek, budgetedHours));
        assertNotNull(task);
    }

     */

    @Then("the error message {string} is given")
    public void alreadyExistsCreateTask(String error){
        assertEquals(error, this.errorMessage.getErrorMessage());
    }
}
