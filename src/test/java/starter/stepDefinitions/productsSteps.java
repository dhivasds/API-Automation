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

//    BDD
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
    public void iValidateAfterGetDetailProducts(){}


//---------------END BDD---------------------------------




























//// -----------------Get List---------------------
//    @Given("I set an endpoint for products list")
//    public void iSetAnEndpointForProductsList() {
//        products.setAnEndpointForProductsList();
//
//    }
//
//    @When("I request GET products list")
//    public void iRequestGETProductsList() {
//        products.setRequestGETProductsList();
//    }
//
//    @And("validate the data detail after get products")
//    public void validateTheDataDetailAfterGetProducts() {
//        products.setValidateTheDataDetailAfterGetProducts();
//    }
//
////  ---------------By ID-----------------------------
//    @Given("I set an endpoint for products id")
//    public void iSetAnEndpointForProductsId() {
//        products.setAnEndpointForProductsId();
//    }
//
//    @When("I request GET products id")
//    public void iRequestGETProductsId() {
//        products.setRequestGETProductsId();
//    }
//
//    @And("validate the data detail after get detail products")
//    public void validateTheDataDetailAfterGetDetailProducts() {
//        products.setValidateTheDataDetailAfterGetDetailProducts();
//    }
//
//    @Given("I set an endpoint for invalid products id")
//    public void iSetAnEndpointForInvalidProductsId() {
//        products.iSetAnEndpointForInvalidProductsId();
//    }
//
//    @When("I request GET invalid products id")
//    public void iRequestGETInvalidProductsId() {
//        products.setRequestGETInvalidProductsId();
//    }
//
//    @And("validate the data detail after failed to get detail products")
//    public void validateTheDataDetailAfterFailedToGetDetailProducts() {
//        products.setValidateTheDataDetailAfterFailedToGetDetailProducts();
//    }



}
