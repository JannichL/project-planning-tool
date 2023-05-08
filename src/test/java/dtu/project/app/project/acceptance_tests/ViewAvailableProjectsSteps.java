package dtu.project.app.project.acceptance_tests;

import dtu.project.app.application.ProjectPlanningApp;
import dtu.project.app.objects.Project;
import dtu.project.app.objects.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class ViewAvailableProjectsSteps {

    private ProjectPlanningApp projectPlanningApp;
    public ViewAvailableProjectsSteps(ProjectPlanningApp projectPlanningApp) {
        this.projectPlanningApp = projectPlanningApp;
    }

    @Given("projects with these names are contained in the database")
    public void projectsWithTheseNamesAreContainedInTheDatabase(List<String> project) {
        for (String name : project) {
            projectPlanningApp.createNewProject(name);
        }
        projectPlanningApp.getAllProjectsViewable();
        for (String name1 : project) {
            assertTrue(projectPlanningApp.projectIsContainedInDatabase(name1));
        }
    }

    @Given("there are available projects for {string}")
    public void availableProjects(String initials){
        projectPlanningApp.addUser(initials);
        projectPlanningApp.userLogin(initials);
        assertNotNull(projectPlanningApp.getMyProjectsViewable());
    }

    @Then("{string}, {string}, {string} and {string} are shown")
    public void projectsAreShown(String name1, String name2, String name3, String name4) {
        projectPlanningApp.getProject(name1);
        projectPlanningApp.getProject(name2);
        projectPlanningApp.getProject(name3);
        projectPlanningApp.getProject(name4);
        assertTrue(projectPlanningApp.projectIsContainedInDatabase(name1));
        assertTrue(projectPlanningApp.projectIsContainedInDatabase(name2));
        assertTrue(projectPlanningApp.projectIsContainedInDatabase(name3));
        assertTrue(projectPlanningApp.projectIsContainedInDatabase(name4));
    }

    @Given("that there are no projects available for {string}")
    public void thatThereAreNoProjectsAvailable(String initials) {
        projectPlanningApp.addUser(initials);
        projectPlanningApp.userLogin(initials);
        assertTrue(projectPlanningApp.getMyProjectsViewable().isEmpty());
    }

    @Then("no projects will be shown")
    public void noProjectsWillBeShown() {
        assertTrue(projectPlanningApp.getMyProjectsViewable().isEmpty());
    }
}
