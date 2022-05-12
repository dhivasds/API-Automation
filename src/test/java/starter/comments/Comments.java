package starter.comments;

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

public class Comments {

    String base_url = "https://be-qa.alta.id/api/products/";
    String Id,token;

    @Step("I set an endpoint for comments")
    public String setAnEndpointForComments() throws IOException {
        this.Id = FileUtils.readFileToString(new File(System.getProperty("user.dir") +
                "/src/test/resources/filejson/Id.json"), StandardCharsets.UTF_8);
        System.out.println(this.Id);
        return base_url + this.Id + "/comments";
    }

    @Step("I request {string} comments")
    public void setRequestComments(String inputC) throws IOException {
        if (inputC.equals("GiveCommentWithValidInput")){
            JSONObject requestBody = new JSONObject();
            requestBody.put("content", "Barangnya berfungsi dengan sangat baik!!");
//           * Catch Token
            this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") +
                    "/src/test/resources/filejson/token.json"), StandardCharsets.UTF_8);
            System.out.println(this.token);
//           * Put Token in header
            SerenityRest.given().header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .body(requestBody.toJSONString()).post(setAnEndpointForComments());

        }else if (inputC.equals("GiveCommentWithNullInput")){
            JSONObject requestBody = new JSONObject();
            requestBody.put("content", null);
//           * Catch Token
            this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") +
                    "/src/test/resources/filejson/token.json"), StandardCharsets.UTF_8);
            System.out.println(this.token);
//           * Put Token in header
            SerenityRest.given().header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .body(requestBody.toJSONString()).post(setAnEndpointForComments());

        }else if (inputC.equals("InvalidToken")){
            JSONObject requestBody = new JSONObject();
            requestBody.put("content", "Barangnya berfungsi dengan sangat baik!!");

            SerenityRest.given().header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + "12345")
                    .body(requestBody.toJSONString()).post(setAnEndpointForComments());
        }else {
            SerenityRest.given().get(setAnEndpointForComments());
        }
    }

    @Step("validate the data detail after {string} comments")
    public void setValidateDataDetailComments(String message){
        if (message.equals("InputValidComment")){
            restAssuredThat(response -> response.body("'data'.'Content'", Matchers.equalTo("Barangnya berfungsi dengan sangat baik!!")));
        }else if (message.equals("InputNullComment")){
            restAssuredThat(response -> response.body("error", Matchers.equalTo("ERROR: new row for relation \"comments\" violates check constraint \"comments_content_check\" (SQLSTATE 23514)")));
        }else if (message.equals("InvalidToken")){
            restAssuredThat(response -> response.body("'error'", Matchers.equalTo("token contains an invalid number of segments")));
        }else {
            Response responseRatings = SerenityRest.lastResponse();
            String getComments = responseRatings.jsonPath().getString("data[0].Content");
            System.out.println(getComments);

            try (FileWriter file = new FileWriter("src/test/resources/filejson/comments.json")) {
                file.write(getComments);
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            restAssuredThat(response -> response.body("data[0].Content", Matchers.equalTo(getComments)));
        }
    }
}
