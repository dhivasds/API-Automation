package starter.category;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class GetListCategory {

    String base_url = "https://be-qa.alta.id/api/";

    @Step("I set an endpoint for user")
    public String setAnEndpointForUser(){ return base_url + "categories";}

    @Step("I send GET HTTP category")
    public void setRequestGETCategoryList() {
        SerenityRest.given().get(setAnEndpointForUser());
    }

    @Step("I validate the status code is {int}")
    public void setValidateTheStatusCodeIs(int sCode) {
        restAssuredThat(response -> response.statusCode(sCode));
    }

    @Step("validate the data detail after get category")
    public void setvalidateTheDataDetailAfterGetCategory() {
// need fix for equals body
        restAssuredThat(response -> response.body("'data'.'ID'",equalTo(1)));
//        restAssuredThat(response -> response.body("'data'.'name'",equalTo("kesehatan")));
    }
}
