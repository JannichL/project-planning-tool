package dtu.project.acceptance_tests;

import dtu.project.app.ProjectPlanningApp;
import dtu.project.app.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class LoginLogoutSteps {

    private ProjectPlanningApp projectPlanningApp;
    private User user;
    public LoginLogoutSteps(ProjectPlanningApp projectPlanningApp) {
        this.projectPlanningApp = projectPlanningApp;
    }

    @Given("that the User is logged in")
    public void thatTheUserIsLoggedIn() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Given("these IDs are contained in the database")
    public void InitialsAreInDatabase(String initials) {
        user = new User(initials);
        throw new io.cucumber.java.PendingException();
    }
    @Given("the User logs out")
    public void theUserLogsOut() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Given("that the User is not logged in")
    public void thatTheUserIsNotLoggedIn() {
        assertFalse(projectPlanningApp.isLoggedIn());
        throw new io.cucumber.java.PendingException();
    }
    @Given("the worker ID is {string}")
    public void theWorkerIDIs(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the User login succeeds")
    public void theUserLoginSucceeds() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the User is logged in")
    public void theUserIsLoggedIn() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the User login fails")
    public void theUserLoginFails() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
