package starter.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.products.Products;

public class productsSteps {

    @Steps
    Products products;

    @Given("I set an {string} for products")
    public void iSetAnEndpoints(String endpoint){
        products.setAnEndpointProducts(endpoint);
    }

    @When("I request {string} products")
    public void iRequestProducts(String inputP){
        products.setRequestProducts(inputP);
    }

    @Then("I validate the status code is {string} in products")
    public void iValidateStatusCodeProducts(String sCodeP){
        products.setValidateStatusCodeProducts(sCodeP);
    }

    @And("Validate the data detail after {string} products")
    public void iValidateAfterGetDetailProducts(String statusP){
        products.setValidateDataDetailProducts(statusP);
    }
}
