package starter.authentication;

import Utils.General;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
        if (input.equals("InputValidRegister")){ // ? Input valid register
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

        }else if (input.equals("InputRegisterNullEmail")){ // ? Input Null email in register
            JSONObject requestBody = new JSONObject();

            requestBody.put("fullname", general.randomName(input));
            requestBody.put("email", null);
            requestBody.put("password", general.randomPassword(input));

            SerenityRest.given().header("Content-Type", "application/json")
                    .body(requestBody.toJSONString()).post(setEndpointForAuth("register"));

        }else if (input.equals("InputRegisterNullPassword")){ // ? Input Null password in register
            JSONObject requestBody = new JSONObject();
            requestBody.put("fullname", general.randomName(input));
            requestBody.put("email", general.randomEmail());
            requestBody.put("password", null);

            SerenityRest.given().header("Content-Type", "application/json")
                    .body(requestBody.toJSONString()).post(setEndpointForAuth("register"));
        }else if (input.equals("ValidInputLogin")) { // ? Input valid login
            JSONObject requestBody = new JSONObject();
            this.email = FileUtils.readFileToString(new File(System.getProperty("user.dir") +
                    "/src/test/resources/filejson/email.json"), StandardCharsets.UTF_8);
            this.password = FileUtils.readFileToString(new File(System.getProperty("user.dir") +
                    "/src/test/resources/filejson/password.json"), StandardCharsets.UTF_8);

            String emailLogin = this.email;
            String passwordLogin = this.password;

            requestBody.put("email", emailLogin);
            requestBody.put("password", passwordLogin);

            SerenityRest.given().header("Content-Type", "application/json")
                    .body(requestBody.toJSONString()).post(setEndpointForAuth("login"));

        }else if (input.equals("InputInvalidEmail")){ // ? Input invalid email & valid password
            JSONObject requestBody = new JSONObject();
            this.password = FileUtils.readFileToString(new File(System.getProperty("user.dir") +
                    "/src/test/resources/filejson/password.json"), StandardCharsets.UTF_8);

            requestBody.put("email", general.randomEmail());
            requestBody.put("password", this.password);

            SerenityRest.given().header("Content-Type", "application/json")
                    .body(requestBody.toJSONString()).post(setEndpointForAuth("login"));
        }else { // ? Input valid email & invalid password
            JSONObject requestBody = new JSONObject();
            this.email = FileUtils.readFileToString(new File(System.getProperty("user.dir") +
                    "/src/test/resources/filejson/email.json"), StandardCharsets.UTF_8);

            requestBody.put("email", this.email);
            requestBody.put("password", general.randomPassword(input));

            SerenityRest.given().header("Content-Type", "application/json")
                    .body(requestBody.toJSONString()).post(setEndpointForAuth("login"));
        }
    }

//    @Step("validate the {string} after authentication")
//    public void SetValidateAfterAuth(String message){
//        if (message.equals("AccountRegister"));
//    }
}
