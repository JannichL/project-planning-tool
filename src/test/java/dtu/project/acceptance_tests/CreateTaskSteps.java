package dtu.project.acceptance_tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateTaskSteps {

    @Given("that the User is logged in")
    public void UserIsLoggedIn() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("there is no ProjectManager elected")
    public void NoProjectManager() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("there is a task with title ?Design?, budgetedHours {int}, startWeek {int}, and endWeek {int}")
    public void TaskExists(Integer int1, Integer int2, Integer int3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the task is not contained in the project")
    public void TaskNotInProject() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the task is added to the project")
    public void AddTask() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the task with title ?Design?, budgetedHours {int}, startWeek {int}, and endWeek {int} is contained in the project")
    public void TaskExistsInProject(Integer int1, Integer int2, Integer int3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("there is a ProjectManager elected")
    public void ProjectManagerExists() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the User is the ProjectManager")
    public void UserIsProjectManager() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the User is not the ProjectManager")
    public void UserIsNotProjectManager() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the error message ?Only project manager can create tasks? is given")
    public void InvalidAccessCreateTask() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
