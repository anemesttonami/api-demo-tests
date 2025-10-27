package in.reqres.clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static in.reqres.specs.RequestSpecs.loginReqSpec;
import static in.reqres.specs.ResponseSpecs.loginRespSpec;
import static io.restassured.RestAssured.given;

public class LoginClient {

    @Step("Логинимся.")
    public Response login(Map<String, String> data, String endpoint) {
        return given().
                spec(loginReqSpec).
                body(data).
                when().
                post(endpoint).
                then().
                spec(loginRespSpec).
                extract().response();
    }
}