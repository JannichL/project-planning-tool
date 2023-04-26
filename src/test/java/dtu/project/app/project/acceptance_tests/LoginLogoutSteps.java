package dtu.project.app.project.acceptance_tests;

import dtu.project.app.application.ProjectPlanningApp;
import dtu.project.app.objects.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

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
    public void InitialsAreInDatabase(List<String> IDs) {
        for(String id : IDs){
            projectPlanningApp.addUserToDatabase(id);
        }
    }
    @Given("the User logs out")
    public void theUserLogsOut() {
        projectPlanningApp.userLogout();
        assertFalse(projectPlanningApp.isLoggedIn());
    }
    @Given("that the User is not logged in")
    public void thatTheUserIsNotLoggedIn() {
        assertFalse(projectPlanningApp.isLoggedIn());
    }
    @Given("the worker ID is {string}")
    public void theWorkerIDIs(String initial) {
        initials = initial;
    }

    @Then("the User is logged in")
    public void theUserIsLoggedIn() {
        assertTrue(projectPlanningApp.isLoggedIn());
    }
    @Then("the User login fails")
    public void theUserLoginFails() {
        projectPlanningApp.userLogin(initials);
        assertFalse(projectPlanningApp.isLoggedIn());
    }

    @When("the User tries to login with the initials {string}")
    public void theUserTriesToLoginWithTheInitials(String initialInput) {
        projectPlanningApp.userLogin(initialInput);
    }

    @Then("the User with the initials {string} is found")
    public void theUserWithTheInitialsIsFound(String initialInput) {
        assertTrue(projectPlanningApp.userIsContainedInDatabase(initialInput));
    }

    @Then("the User is not logged in")
    public void theUserIsNotLoggedIn() {
        assertFalse(projectPlanningApp.isLoggedIn());
    }

    @Given("the User is already logged in")
    public void theUserIsAlreadyLoggedIn() {
        assertTrue(projectPlanningApp.isLoggedIn());
    }


    @And("the User is logged in with the initials {string}")
    public void theUserIsLoggedInWithTheInitials(String initials) {
        projectPlanningApp.userLogin(initials);
        assertTrue(projectPlanningApp.isLoggedIn());
    }
}
