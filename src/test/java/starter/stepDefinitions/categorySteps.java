package starter.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.category.GetListCategory;

public class categorySteps {
    @Steps
    GetListCategory getListCategory;

    @Given("I set an endpoint for user")
    public void iSetAnEndpointForUser() {
        getListCategory.setAnEndpointForUser();
    }

    @When("I request GET category list")
    public void iRequestGETCategoryList() {
        getListCategory.setRequestGETCategoryList();
    }

    @Then("I validate the status code is {int}")
    public void iValidateTheStatusCodeIs(int sCode) {
        getListCategory.setValidateTheStatusCodeIs(sCode);
    }

    @And("validate the data detail after get category")
    public void validateTheDataDetailAfterGetCategory() {
        getListCategory.setvalidateTheDataDetailAfterGetCategory();
    }



}
