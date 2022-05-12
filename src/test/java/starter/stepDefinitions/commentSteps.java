package starter.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;
import starter.comments.Comments;

import java.io.IOException;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class commentSteps {

    @Steps
    Comments comments;

    @Given("I set an endpoint for comments")
    public void iSetAnEndpointForComments() throws IOException {
        comments.setAnEndpointForComments();
    }

    @When("I request {string} comments")
    public void iRequestComments(String inputC) throws IOException {
        comments.setRequestComments(inputC);
    }

    @And("validate the data detail after {string} comments")
    public void validateTheDataDetailAfterComments(String message) {
        comments.setValidateDataDetailComments(message);
    }


}
