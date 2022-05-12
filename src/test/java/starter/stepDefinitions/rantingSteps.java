package starter.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.ratings.Ratings;

import java.io.IOException;

public class rantingSteps {

    @Steps
    Ratings ratings;

    @Given("I set an {string} for ratings")
    public void iSetAnForRantings(String endpoint) throws IOException {
        ratings.setAnEndpointForRatings(endpoint);
    }

    @When("I request {string} ratings")
    public void iRequestRantings(String inputR) throws IOException {
        ratings.setRequestRatings(inputR);
    }

    @And("validate the data detail after {string} ratings")
    public void validateTheDataDetailAfterRantings(String message) throws IOException {
        ratings.setValidateDataDetailRatings(message);
    }
}
