package dtu.project.acceptance_tests;

import dtu.project.app.Project;
import dtu.project.app.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class BecomeProjectManager {
    private Project project;
    private User user;

    @Given("A project is called Project1")
    public void projectThatIsCalledPoject1() {
        // create a project with no project manager assigned
        project = new Project("Project1", 100, 1, 10);
    }

    @And("A User is called AB")
    public void userIsCalledAB() {
        // create a user
        user = new User("AB");
    }

    @And("no project manager is currently assigned to a project")
    public void noProjectManagerIsCurrentlyAssignedToAProject() {
        Assert.assertFalse(project.getIsProjectManagerAssigned());
    }

    @Then("the system will assign the User as the project manager")
    public void theSystemWillAssignTheUserAsTheProjectManager() {
        // assign the user as the project manager
        project.setProjectManager(user.getInitials());

        // check if user is assigned as the project manager
        Assert.assertEquals(user.getInitials(), project.getProjectManager());
    }
}






