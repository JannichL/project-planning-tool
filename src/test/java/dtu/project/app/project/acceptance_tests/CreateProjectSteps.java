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

    private ProjectPlanningApp projectPlanningApp;
    private Project project;
    private User user;

    public CreateProjectSteps(ProjectPlanningApp projectPlanningApp) {
        this.projectPlanningApp = projectPlanningApp;
    }
    @Given("these Projects are contained in the database")
    public void projectsWithTheseNamesAreContainedInTheDatabase(List<String> names) {
        names = projectPlanningApp.getProjectNames();
        for (String name : names){
            projectPlanningApp.projectIsContainedInDatabase(name);
        }
    }
    @Given("a project with {string} name is not in the database")
    public void aProjectWithThatNameIsNotInTheDatabase(String projectName) {
        assertFalse(projectPlanningApp.projectIsContainedInDatabase("Project5"));
    }
    @When("the project {string} is created its stored in the database")
    public void theProjectIsStoredInTheDatabase(String Project5) {
        Project project = new Project(Project5, 10, 1, 2);
        projectPlanningApp.addProjectToDatabase(project);
    }
}