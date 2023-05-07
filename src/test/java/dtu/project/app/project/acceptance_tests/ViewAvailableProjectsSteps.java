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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ViewAvailableProjectsSteps {

    private Project project;
    private User user;

    private ProjectPlanningApp projectPlanningApp;
    public ViewAvailableProjectsSteps(ProjectPlanningApp projectPlanningApp) {
        this.projectPlanningApp = projectPlanningApp;
    }

    @Given("projects with these names are contained in the database")
    public void projectsWithTheseNamesAreContainedInTheDatabase(List<String> names) {
        names = projectPlanningApp.getProjectNames();
        for (String name : names) {
            projectPlanningApp.projectIsContainedInDatabase(name);
        }
    }

    @Then("“Project1”, “Project2”, “Project3” and “Project4” are shown")
    public void projectsAreShown() {
        List<String> expectedProjectNames = Arrays.asList("Project1", "Project2", "Project3", "Project4");
        List<String> actualProjectNames = projectPlanningApp.getProjectNames();
    }

    @Given("that there are no projects available")
    public void thatThereAreNoProjectsAvailable() {
        List<String> projectNames = projectPlanningApp.getProjectNames();
        if (projectNames.isEmpty())
            System.out.println("No projects are currently in the database");
    }

    @Then("no projects will be shown")
    public void noProjectsWillBeShown() {
        assertTrue(projectPlanningApp.getProjectNames().isEmpty());
    }

}
