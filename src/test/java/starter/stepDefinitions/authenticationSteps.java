package starter.stepDefinitions;

import Utils.General;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.authentication.Authentication;

public class authenticationSteps {
    @Steps
    Authentication authentication;
    @Steps
    General general;

    @Given("I set an {string} for authentication")
    public void iSetAnForAuthentication(String endpoint) {
        authentication.setEndpointForAuth(endpoint);
    }

    @When("I request {string} POST authentication")
    public void iRequestPOSTAuthentication(String input) {
        authentication.setRequestPostAuth(input);
    }

    @And("validate the {string} after authentication")
    public void validateTheAfterAuthentication(String message) {
    }

    @And("get data if {string} for other request")
    public void getDataIfForOtherRequest(String message1) {
    }
}
