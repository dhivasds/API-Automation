package starter.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.category.Category;

public class categorySteps {
    @Steps
    Category category;

// Get Category
    @Given("I set an endpoint for user")
    public void iSetAnEndpointForUser() {
        category.setAnEndpointForUser();
    }

    @When("I request GET category list")
    public void iRequestGETCategoryList() {
        category.setRequestGETCategoryList();
    }

    @Then("I validate the status code is {int}")
    public void iValidateTheStatusCodeIs(int code) {
        category.setValidateTheStatusCodeIs(code);
    }

    @And("validate the data detail after get category")
    public void validateTheDataDetailAfterGetCategory() {
        category.setValidateTheDataDetailAfterGetCategory();
    }


// Create Category
    @When("I request {string} POST category")
    public void iRequestPOSTCategory(String input) {
        category.setRequestPOSTCategory(input);
    }

    @Then("I validate the status code is {string}")
    public void iValidateTheStatusCodeIs(String sCode) {
        category.setValidateTheStatusCodeIs(sCode);
    }

    @And("validate the data detail after {string} category")
    public void validateTheDataDetailAfterCategory(String status) {
        category.setValidateTheDataDetailAfterGetCategory(status);
    }



}
