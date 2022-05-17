package starter.ratings;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class Ratings {



    String base_url = "https://be-qa.alta.id/api/products/";
    String Id,token;


    @Step("I set an {string} for ratings")
    public String setAnEndpointForRatings(String endpoint) throws IOException {
        if (endpoint.equals("validId")){
            this.Id = FileUtils.readFileToString(new File(System.getProperty("user.dir") +
                    "/src/test/resources/filejson/Id.json"), StandardCharsets.UTF_8);
            System.out.println(this.Id);
            return base_url + this.Id + "/ratings";

        }else {
            return base_url + 9323 + "/ratings";
        }
    }

    @Step("I request {string} ratings")
    public void setRequestRatings(String inputR) throws IOException {
        if (inputR.equals("validGetRantingsById")){ //? Get Rantings By Id
            SerenityRest.given().get(setAnEndpointForRatings("validId"));

        }else if (inputR.equals("validGiveRantingsById")){ //? Give Rating to product with valid Id & valid token
            JSONObject requestBody = new JSONObject();
            requestBody.put("count", 2);
//           * Read Token
            this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") +
                    "/src/test/resources/filejson/token.json"), StandardCharsets.UTF_8);
            System.out.println(this.token);
//           * Put Token in header
            SerenityRest.given().header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .body(requestBody.toJSONString()).post(setAnEndpointForRatings("validId"));
        }else if (inputR.equals("invalidGiveRantingsById")){ //? Give Rating to product with invalid Id & valid token
            JSONObject requestBody = new JSONObject();
            requestBody.put("count", 2);
//           * Read Token
            this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") +
                    "/src/test/resources/filejson/token.json"), StandardCharsets.UTF_8);
            System.out.println(this.token);
//           * Put Token in header
            SerenityRest.given().header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .body(requestBody.toJSONString()).post(setAnEndpointForRatings("invalidId"));

        }else { //? Give Rating to product with invalid token & valid Id
            JSONObject requestBody = new JSONObject();
            requestBody.put("count", 2);

            SerenityRest.given().header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + "12345")
                    .body(requestBody.toJSONString()).post(setAnEndpointForRatings("validId"));
        }
    }

    @Step("validate the data detail after {string} ratings")
    public void setValidateDataDetailRatings(String message) throws IOException {
        if (message.equals("GetRatingsById")){

            Response responseRatings = SerenityRest.lastResponse();
            String getRatings = responseRatings.jsonPath().getString("data");
            System.out.println(getRatings);

            try (FileWriter file = new FileWriter("src/test/resources/filejson/ratings.json")) {
                file.write(getRatings);
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            restAssuredThat(response -> response.body("data", Matchers.equalTo(Integer.parseInt(getRatings))));


        }else if (message.equals("DetailRantingProductById")){
            restAssuredThat(response -> response.body("'data'.'Name'", Matchers.equalTo("Tensi Darah Elektrik")));
            restAssuredThat(response -> response.body("'data'.'Price'", Matchers.equalTo(1000)));
        }else if (message.equals("ErrorRecordNotFound")){
            restAssuredThat(response -> response.body("'error'", Matchers.equalTo("record not found")));
        }else {
            restAssuredThat(response -> response.body("'error'", Matchers.equalTo("token contains an invalid number of segments")));
        }
    }



}
