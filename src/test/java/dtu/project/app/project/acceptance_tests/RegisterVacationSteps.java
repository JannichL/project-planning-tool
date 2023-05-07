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

public class RegisterVacationSteps {

    private User user;
    private ProjectPlanningApp projectPlanningApp;

    public RegisterVacationSteps(ProjectPlanningApp projectPlanningApp) {
        this.projectPlanningApp = projectPlanningApp;
    }

    @Given("the user enters the vacation time details")
    public void theUserEntersTheVacationTimeDetails(int days) {
        user.addVacationDays(days);
    }

    @Then("its registered in the database")
    public void itsRegisteredInTheDatabase() {
        user.getVacationDays();
    }
}
