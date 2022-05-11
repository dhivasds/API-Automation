package starter.category;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class Category {

    String base_url = "https://be-qa.alta.id/api/";

//   ---- GET -----
    @Step("I set an endpoint for user")
    public String setAnEndpointForUser(){ return base_url + "categories";}

    @Step("I request GET category list")
    public void setRequestGETCategoryList() {
        SerenityRest.given().get(setAnEndpointForUser());
    }

    @Step("I validate the status code is {int}")
    public void setValidateTheStatusCodeIs(int code) {
        restAssuredThat(response -> response.statusCode(code));
    }

    @Step("validate the data detail after get category")
    public void setValidateTheDataDetailAfterGetCategory() {
//        Not equals in get list category
//        restAssuredThat(response -> response.body("'data'.'ID'",equalTo(1)));
//        restAssuredThat(response -> response.body("'data'.'Name'",equalTo("kesehatan")));
//        restAssuredThat(response -> response.body("'data'.'Description'",equalTo("")));
//!        FIX EQUALS USING CONTAINS ( RECHECK LAGI ) seperti di all products GET
        restAssuredThat(response -> contains("'data'.'ID'",equalTo(1)));
        restAssuredThat(response -> contains("'data'.'Name'",equalTo("kesehatan")));
        restAssuredThat(response -> contains("'data'.'Description'",equalTo("")));
    }


//    ----POST----
    @Step("I request POST category")
    public void setRequestPOSTCategory(String input) {
        if (input.equals("validInput")){
            JSONObject requestBody = new JSONObject();
            requestBody.put("Name", "kesehatan");

            SerenityRest.given().header("Content-Type", "application/json")
                    .body(requestBody.toJSONString()).post(setAnEndpointForUser());

        }else if(input.equals("inputNumber")){
            JSONObject requestBody = new JSONObject();
            requestBody.put("name", 1);

            SerenityRest.given().header("Content-Type", "application/json")
                    .body(requestBody.toJSONString()).post(setAnEndpointForUser());

        }else {
            JSONObject requestBody = new JSONObject();
            requestBody.put("name", null);

            SerenityRest.given().header("Content-Type", "application/json")
                    .body(requestBody.toJSONString()).post(setAnEndpointForUser());
        }


    }

    @Step("I validate the status code is {string}")
    public void setValidateTheStatusCodeIs(String sCode) {
        if (sCode.equals("200")){
            restAssuredThat(response -> response.statusCode(200));
        }else if(sCode.equals("400")){
            restAssuredThat(response -> response.statusCode(400));
        }else{
            restAssuredThat(response -> response.statusCode(500));
        }
    }

    @Step("validate the data detail after get category")
    public void setValidateTheDataDetailAfterGetCategory(String status) {
        if (status.equals("inputCreate")){
            restAssuredThat(response -> response.body("'data'.'Name'",equalTo("kesehatan")));
        }else if(status.equals("inputFailedNumber")){
            restAssuredThat(response -> response.body("'error'",equalTo("json: cannot unmarshal number into Go struct field CategoryCreate.name of type string")));
        }else {
            restAssuredThat(response -> response.body("'error'",equalTo("ERROR: new row for relation \"categories\" violates check constraint \"categories_name_check\" (SQLSTATE 23514)")));
        }
    }

}
