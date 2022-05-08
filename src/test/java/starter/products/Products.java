package starter.products;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;

public class Products {

    String base_url = "https://be-qa.alta.id/api/";

// BDD
    @Step("I set an {string} for products")
    public String setAnEndpointProducts(@NotNull String endpoint){
        if (endpoint.equals("productsByInvalidId")){
            return base_url + "products/1";
        }else if (endpoint.equals("productsByValidId")){
            return base_url + "products/368";
        }else {
            return base_url + "products";
        }
    }

    @Step("I request {string} products")
    public void setRequestProducts(String inputP){
        if (inputP.equals("validGetAllProducts")){
            SerenityRest.given().get(setAnEndpointProducts("products"));
        }else if (inputP.equals("validGetProductById")){
            SerenityRest.given().get(setAnEndpointProducts("productsByValidId"));
        }else if (inputP.equals("invalidGetProductById")){
            SerenityRest.given().get(setAnEndpointProducts("productsByInvalidId"));
        }else if (inputP.equals("validCreateProduct")){
            JSONObject requestBody = new JSONObject();
            requestBody.put("name", "Tensi Darah Manual");
            requestBody.put("price", 1000);
//            Need Fix kalau mau post pakai categories
//            requestBody.put("categories[0]",3 );
//            requestBody.put("categories[1]",6 );
            SerenityRest.given().header("Content-Type", "application/json")
                    .body(requestBody.toJSONString()).post(setAnEndpointProducts("products"));
        }else {
            JSONObject requestBody = new JSONObject();
            requestBody.put("name", 1);
            requestBody.put("price", 1000);

            SerenityRest.given().header("Content-Type", "application/json")
                    .body(requestBody.toJSONString()).post(setAnEndpointProducts("products"));
        }
    }

    @Step("I validate the status code is {string} in products")
    public void setValidateStatusCodeProducts(String sCodeP){
        if (sCodeP.equals("200")){
            SerenityRest.then().statusCode(200);
        }else if (sCodeP.equals("404")){
            SerenityRest.then().statusCode(404);
        }else {
            SerenityRest.then().statusCode(400);
        }
    }
// ------- END BDD ----------














//    // -----------------Scenario---------------------
//    @Step("I set an endpoint for products list")
//    public String setAnEndpointForProductsList() {
//        return base_url + "products";
//    }
//
//    @Step("I request GET products list")
//    public void setRequestGETProductsList() {
//        SerenityRest.given().get(setAnEndpointForProductsList());
//    }
//
//    @Step("validate the data detail after get products")
//    public void setValidateTheDataDetailAfterGetProducts() {
////        Need fix for equals, not match
//        restAssuredThat(response -> response.body("'data'.'ID'",equalTo(368)));
//        restAssuredThat(response -> response.body("'data'.'Name'",equalTo("Tensi Darah Elektrik")));
//    }
////    ------------------------------------------
//    @Step("I set an endpoint for products id")
//    public String setAnEndpointForProductsId() {
//        return base_url + "products/293";
//    }
//
//    @When("I request GET products id")
//    public void setRequestGETProductsId() {
//        SerenityRest.given().get(setAnEndpointForProductsId());
//    }
//
//    @Step("validate the data detail after get detail products")
//    public void setValidateTheDataDetailAfterGetDetailProducts() {
//        restAssuredThat(response -> response.body("'data'.'ID'",equalTo(293)));
//        restAssuredThat(response -> response.body("'data'.'Name'",equalTo("Tensi Darah Elektrik")));
//    }
//
//    @Step("I set an endpoint for invalid products id")
//    public String iSetAnEndpointForInvalidProductsId() {
//        return base_url + "products/1";
//    }
//
//    @Step("I request GET invalid products id")
//    public void setRequestGETInvalidProductsId() {
//        SerenityRest.given().get(iSetAnEndpointForInvalidProductsId());
//    }
//
//    @Step("validate the data detail after failed to get detail products")
//    public void setValidateTheDataDetailAfterFailedToGetDetailProducts() {
//        restAssuredThat(response -> response.body("'error'",equalTo("record not found")));
//    }
}
