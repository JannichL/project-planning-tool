package dtu.project.app.project.acceptance_tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import dtu.project.app.application.InvalidOperationException;
import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.Assertions;

import dtu.project.app.application.ProjectPlanningApp;
import dtu.project.app.objects.Project;
import dtu.project.app.objects.User;

public class LogHoursStepTest {

    private ProjectPlanningApp projectPlanningApp;
    {
        try {
            projectPlanningApp = new ProjectPlanningApp();
        } catch (InvalidOperationException e) {
            throw new RuntimeException(e);
        }
    }
    @Before
    public void setUp() {
        // create a new ProjectPlanningApp instance and add some projects
        projectPlanningApp.createNewProject("Project 1");
        projectPlanningApp.createNewProject("Project 2");
    }

    @Test
    public void testThatThereAreProjectsAvailable() {
        // create a new LogHoursStep instance and call the 'thatThereAreProjectsAvailable' method
        LogHoursStep step = new LogHoursStep(projectPlanningApp);
        step.thatThereAreProjectsAvailable();

        // assert that the project names list is not empty
        Assertions.assertFalse(projectPlanningApp.getProjectNames().isEmpty());
    }

    @Test
    public void testTheUserLogsHoursOfWorkOnAProjectWithTheIDOnTask() {

        projectPlanningApp.createNewProject("Skyscraper");
        projectPlanningApp.addUserToDatabase("deez");

        LogHoursStep step = new LogHoursStep(projectPlanningApp);
        step.thereIsAUserWithTheInitials("deez");

        step.theUserLogsHoursOfWorkOnAProjectWithTheIDOnTask(1, "Skyscraper", "Design");

        Assertions.assertTrue(projectPlanningApp.getCurrentUser().getLoggedHours("Skyscraper", "Design", 2));
    }

}