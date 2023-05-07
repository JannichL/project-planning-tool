package dtu.project.app.project.acceptance_tests;
import dtu.project.app.application.ProjectPlanningApp;
import dtu.project.app.objects.Project;
import dtu.project.app.objects.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegisterSickSteps {

    private ProjectPlanningApp projectPlanningApp;

    private User user;

    public RegisterSickSteps(ProjectPlanningApp projectPlanningApp) {
        this.projectPlanningApp = projectPlanningApp;
    }

    @Given("the user enters the sick time details")
    public void theUserEntersTheSickTimeDetails(int days) {
        user.addSickDays(days);
    }

    @Then("its registered in the database")
    public void itsRegisteredInTheDatabase() {
        user.getSickDays();
    }
}

