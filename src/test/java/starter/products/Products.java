package starter.products;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class Products {

    String base_url = "https://be-qa.alta.id/api/";


    // -----------------Scenario---------------------
    @Step("I set an endpoint for products list")
    public String setAnEndpointForProductsList() {
        return base_url + "products";
    }

    @Step("I request GET products list")
    public void setRequestGETProductsList() {
        SerenityRest.given().get(setAnEndpointForProductsList());
    }

    @Step("validate the data detail after get products")
    public void setValidateTheDataDetailAfterGetProducts() {
//        Need fix for equals, not match
        restAssuredThat(response -> response.body("'data'.'ID'",equalTo(368)));
        restAssuredThat(response -> response.body("'data'.'Name'",equalTo("Tensi Darah Elektrik")));
    }
//    ------------------------------------------
    @Step("I set an endpoint for products id")
    public String setAnEndpointForProductsId() {
        return base_url + "products/293";
    }

    @Step("validate the data detail after get detail products")
    public void setValidateTheDataDetailAfterGetDetailProducts() {
        restAssuredThat(response -> response.body("'data'.'ID'",equalTo(293)));
        restAssuredThat(response -> response.body("'data'.'Name'",equalTo("Tensi Darah Elektrik")));
    }

    @Step("I set an endpoint for invalid products id")
    public String iSetAnEndpointForInvalidProductsId() {
        return base_url + "products/1";
    }

    @Step("validate the data detail after failed to get detail products")
    public void setValidateTheDataDetailAfterFailedToGetDetailProducts() {
        restAssuredThat(response -> response.body("'error'",equalTo("record not found")));
    }
}
