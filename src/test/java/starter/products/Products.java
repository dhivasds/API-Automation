package starter.products;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class Products {

    String base_url = "https://be-qa.alta.id/api/";


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

    @Step("Validate the data detail after {string} products")
    public void setValidateDataDetailProducts(String statusP){
        if (statusP.equals("getProducts")){
//          Equals not match, yang ke ambil array
            restAssuredThat(response -> response.body("'data'.'ID'",equalTo(368)));
            restAssuredThat(response -> response.body("'data'.'Name'",equalTo("Tensi Darah Elektrik")));
        }else if (statusP.equals("getProductById")){
            restAssuredThat(response -> response.body("'data'.'ID'",equalTo(368)));
            restAssuredThat(response -> response.body("'data'.'Name'",equalTo("Tensi Darah Elektrik")));
            restAssuredThat(response -> response.body("'data'.'Price'",equalTo(3200000)));
        }else if (statusP.equals("failedGetProductById")){
            restAssuredThat(response -> response.body("'error'",equalTo("record not found")));
        }else if (statusP.equals("createProduct")){
            restAssuredThat(response -> response.body("'data'.'Name'",equalTo("Tensi Darah Manual")));
            restAssuredThat(response -> response.body("'data'.'Price'",equalTo(1000)));
        }else {
            restAssuredThat(response -> response.body("'error'",equalTo("json: cannot unmarshal number into Go struct field ProductCreate.name of type string")));
        }
    }
}
