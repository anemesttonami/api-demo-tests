package in.reqres.clients;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import java.util.Map;

import static in.reqres.utils.AllureFormatter.setReqRespTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class LoginClient {

    @Step("Логинимся.")
    public String login(Map<String, String> data) {
        return given()
                .basePath("/api/login").
                header("x-api-key", "reqres-free-v1").
                filter(setReqRespTemplates()).
                contentType(JSON).
                body(data).
                log().
                uri().
                log().
                body().
                when().
                post().
                then().
                statusCode(200).
                contentType(ContentType.JSON).extract().response().jsonPath().get("token");
    }
}