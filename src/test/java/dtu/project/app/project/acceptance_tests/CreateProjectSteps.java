package dtu.project.app.project.acceptance_tests;

import dtu.project.app.application.ProjectPlanningApp;
import dtu.project.app.objects.Project;
import dtu.project.app.objects.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CreateProjectSteps {

    private Project name;

    private ProjectPlanningApp projectPlanningApp;

    public CreateProjectSteps(ProjectPlanningApp projectPlanningApp) {
        this.projectPlanningApp = projectPlanningApp;
    }
    @Given("these Projects are contained in the database")
    public void projectsWithTheseNamesAreContainedInTheDatabase(List<String> project) {
        for (String name : project) {
            projectPlanningApp.createNewProject(name);
        }
        projectPlanningApp.getAllProjectsViewable();
        for (String name1 : project) {
            assertTrue(projectPlanningApp.projectIsContainedInDatabase(name1));
        }
    }
    @Given("a project with {string} name is not in the database")
    public void aProjectWithThatNameIsNotInTheDatabase(String projectName) {
        assertFalse(projectPlanningApp.projectIsContainedInDatabase(projectName));
    }
    @When("the project {string} is created its stored in the database")
    public void theProjectIsStoredInTheDatabase(String Project5) {
        projectPlanningApp.createNewProject(Project5);
        assertTrue(projectPlanningApp.projectIsContainedInDatabase(Project5));
    }
}