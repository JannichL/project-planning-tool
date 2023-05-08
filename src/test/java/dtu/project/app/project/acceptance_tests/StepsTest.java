package dtu.project.app.project.acceptance_tests;

import dtu.project.app.application.InvalidOperationException;

import dtu.project.app.objects.Project;
import dtu.project.app.objects.Task;
import org.junit.jupiter.api.Assertions;

import dtu.project.app.application.ProjectPlanningApp;
import org.junit.jupiter.api.Test;

public class StepsTest {

    private Project project;
    private ProjectPlanningApp projectPlanningApp;
    {
        try {
            projectPlanningApp = new ProjectPlanningApp();
        } catch (InvalidOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createNewProjectTest(){
        projectPlanningApp.createNewProject("Project6");
        Assertions.assertTrue(projectPlanningApp.projectIsContainedInDatabase("Project6"));
    }

    @Test
    public void viewAvailableProjectsTest(){
        projectPlanningApp.createNewProject("Project5");
        projectPlanningApp.createNewProject("Project6");
        projectPlanningApp.createNewProject("Project7");

        Assertions.assertEquals(projectPlanningApp.projectIsContainedInDatabase("Project5"), true);
        Assertions.assertEquals(projectPlanningApp.getProject("Project7"), "Project7");
        Assertions.assertEquals(projectPlanningApp.getProject("Project6"), "Project6");
    }

    @Test
    public void createTaskTest(Task task){
        try {
            project.addTask(task);
        } catch (InvalidOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteTaskTest(Task task){
        try {
            project.removeTask("task123");
        } catch (InvalidOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void loginLogoutTest(){
        projectPlanningApp.addUser("huba");

        Assertions.assertTrue(projectPlanningApp.userIsContainedInDatabase("huba"));
        Assertions.assertTrue(projectPlanningApp.userIsContainedInDatabase("ekki"));
    }
}
