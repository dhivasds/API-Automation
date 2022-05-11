package starter.stepDefinitions;

import Utils.General;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.authentication.Authentication;

import java.io.IOException;

public class authenticationSteps {
    @Steps
    Authentication authentication;


    @Given("I set an {string} for authentication")
    public void iSetAnForAuthentication(String endpoint) {
        authentication.setEndpointForAuth(endpoint);
    }

    @When("I request {string} POST authentication")
    public void iRequestPOSTAuthentication(String input) throws IOException {
        authentication.setRequestPostAuth(input);
    }

    @And("validate the data detail {string} after authentication")
    public void validateTheAfterAuthentication(String message) throws IOException {
        authentication.SetValidateAfterAuth(message);
    }

}
