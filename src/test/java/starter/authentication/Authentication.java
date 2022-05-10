package starter.authentication;

import Utils.General;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Authentication {
    String base_url = "https://be-qa.alta.id/api/auth/";
    String email,password;

    @Steps
    General general;

    @Step("I set an {string} for authentication")
    public String setEndpointForAuth(String endpoint){
        if (endpoint.equals("register")){
            return base_url + "register";
        }else {
            return base_url + "login";
        }
    }

    @Step("I request {string} POST authentication")
    public void setRequestPostAuth(String input) throws IOException {
        if (input.equals("ValidInputRegister")){
            JSONObject requestBody = new JSONObject();
            this.email = general.randomEmail();
            this.password = general.randomPassword(input);
            try (FileWriter file = new FileWriter("src/test/resources/filejson/email.json")) {
                file.write(this.email);
                file.flush();
            } try (FileWriter file = new FileWriter("src/test/resources/filejson/password.json")) {
                file.write(this.password);
                file.flush();
            }catch (IOException e) {
                e.printStackTrace();
            }

            requestBody.put("fullname", general.randomName(input));
            requestBody.put("email",this.email);
            requestBody.put("password", this.password);

            SerenityRest.given().header("Content-Type", "application/json")
                    .body(requestBody.toJSONString()).post(setEndpointForAuth("register"));

        }else if (input.equals("InputSameDataRegister")){
            JSONObject requestBody = new JSONObject();

//TODO      Yang diinput bukan same data tapi int password
            requestBody.put("fullname", "Dhivas Dharma");
            requestBody.put("email", "alexunder@email.com");
            requestBody.put("password", 1);

            SerenityRest.given().header("Content-Type", "application/json")
                    .body(requestBody.toJSONString()).post(setEndpointForAuth("register"));

        }else if (input.equals("ValidInputLogin")){
            JSONObject requestBody = new JSONObject();

//TODO      EMAIL HARUS MATCH SAMA REGISTER (BLM SUCCESSFLY)
            requestBody.put("email", "alexunder@email.com");
            requestBody.put("password", "123123123");

            SerenityRest.given().header("Content-Type", "application/json")
                    .body(requestBody.toJSONString()).post(setEndpointForAuth("login"));

        }else if (input.equals("InputInvalidEmail")){
            JSONObject requestBody = new JSONObject();
            requestBody.put("email", "alexunders@.com");
            requestBody.put("password", "123123123");

            SerenityRest.given().header("Content-Type", "application/json")
                    .body(requestBody.toJSONString()).post(setEndpointForAuth("login"));
        }else {
            JSONObject requestBody = new JSONObject();
            requestBody.put("email", "alexunder@email.com");
            requestBody.put("password", "12");

            SerenityRest.given().header("Content-Type", "application/json")
                    .body(requestBody.toJSONString()).post(setEndpointForAuth("login"));
        }
    }

//    @Step("validate the {string} after authentication")
//    public void SetValidateAfterAuth(String message){
//        if (message.equals("AccountRegister"));
//    }
}
