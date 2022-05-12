package starter.products;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class Products {

    String base_url = "https://be-qa.alta.id/api/";


    @Step("I set an {string} for products")
    public String setAnEndpointProducts(@NotNull String endpoint){
        if (endpoint.equals("productsByInvalidId")){
            return base_url + "products/1";
        }else if (endpoint.equals("productsByValidId")){
            return base_url + "products/430";
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
            List<Integer> data = new ArrayList<>();

            requestBody.put("name", "Tensi Darah Elektrik");
            requestBody.put("price", 1000);
            data.add(3);
            data.add(6);
            requestBody.put("categories", data);

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
//          * Karna list product ketika di GET, akan terus berubah. maka di Catch namanya agar dinamis
            Response responseName = SerenityRest.lastResponse();
            String getNameProduct = responseName.jsonPath().getString("data[0].Name");
            System.out.println(getNameProduct);
            try (FileWriter file = new FileWriter("src/test/resources/filejson/nameProduct.json")) {
                file.write(getNameProduct);
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            restAssuredThat(response -> response.body("data[0].Name",equalTo(getNameProduct)));

        }else if (statusP.equals("getProductById")){
            restAssuredThat(response -> response.body("'data'.'ID'",equalTo(430)));
            restAssuredThat(response -> response.body("'data'.'Name'",equalTo("Tensi Darah Elektrik")));
            restAssuredThat(response -> response.body("'data'.'Price'",equalTo(240000)));

        }else if (statusP.equals("failedGetProductById")){
            restAssuredThat(response -> response.body("'error'",equalTo("record not found")));

        }else if (statusP.equals("createProduct")){
            Response responseId = SerenityRest.lastResponse();
            String getId = responseId.jsonPath().getString("'data'.'ID'");
            System.out.println(getId);
            try (FileWriter file = new FileWriter("src/test/resources/filejson/Id.json")) {
                file.write(getId);
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            restAssuredThat(response -> response.body("'data'.'Name'",equalTo("Tensi Darah Elektrik")));
            restAssuredThat(response -> response.body("'data'.'Price'",equalTo(1000)));

        }else {
            restAssuredThat(response -> response.body("'error'",equalTo("json: cannot unmarshal number into Go struct field ProductCreate.name of type string")));
        }
    }
}
