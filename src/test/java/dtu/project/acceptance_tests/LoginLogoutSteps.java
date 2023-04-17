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
    private String initials;

    public LoginLogoutSteps(ProjectPlanningApp projectPlanningApp) {
        this.projectPlanningApp = projectPlanningApp;
    }

    @Given("these IDs are contained in the database")
    public void InitialsAreInDatabase(String initials) {
        user = new User(initials);
        throw new io.cucumber.java.PendingException();
    }
    @Given("the User logs out")
    public void theUserLogsOut() {
        projectPlanningApp.userLogout();
        assertFalse(projectPlanningApp.isLoggedIn());
        throw new io.cucumber.java.PendingException();
    }
    @Given("that the User is not logged in")
    public void thatTheUserIsNotLoggedIn() {
        assertFalse(projectPlanningApp.isLoggedIn());
        throw new io.cucumber.java.PendingException();
    }
    @Given("the worker ID is {string}")
    public void theWorkerIDIs(String initial) {
        this.initials = initial;
        throw new io.cucumber.java.PendingException();
    }
    @Then("the User login succeeds")
    public void theUserLoginSucceeds() {
        assertTrue(projectPlanningApp.userLogin(initials));
        throw new io.cucumber.java.PendingException();
    }
    @Then("the User is logged in")
    public void theUserIsLoggedIn() {
        assertTrue(projectPlanningApp.isLoggedIn());
        throw new io.cucumber.java.PendingException();
    }
    @Then("the User login fails")
    public void theUserLoginFails() {
        assertFalse(projectPlanningApp.userLogin(initials));
        throw new io.cucumber.java.PendingException();
    }

    @When("the User tries to login with the initials {string}")
    public void theUserTriesToLoginWithTheInitials(String initialInput) {
        projectPlanningApp.userLogin(initialInput);
    }

    @Then("the User with the initials {string} is found")
    public void theUserWithTheInitialsIsFound() {
        assertTrue(projectPlanningApp.userIsContainedInDatabase(initials));
    }

    @Then("the User is not logged in")
    public void theUserIsNotLoggedIn() {
        assertFalse(projectPlanningApp.isLoggedIn());
    }
}
