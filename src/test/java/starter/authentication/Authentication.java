package starter.authentication;

import Utils.General;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;

public class Authentication {

    String base_url = "https://be-qa.alta.id/api/auth/";


    @Step("I set an {string} for authentication")
    public String setEndpointForAuth(String endpoint){
        if (endpoint.equals("register")){
            return base_url + "register";
        }else {
            return base_url + "login";
        }
    }

    @Step("I request {string} POST authentication")
    public void setRequestPostAuth(String input){
        if (input.equals("ValidInputRegister")){
            JSONObject requestBody = new JSONObject();
            requestBody.put("fullname", "Dhivas Dharma");
            requestBody.put("email", "alexunder1@email.com");
            requestBody.put("password", "123123123");

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

}
