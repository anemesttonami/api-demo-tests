package in.reqres.specs;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static in.reqres.utils.AllureFormatter.setReqRespTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class LoginSpec {

    public static RequestSpecification baseLoginReqSpec = with().
            baseUri("https://reqres.in").
            basePath("/api/login").
            header("x-api-key", "reqres-free-v1").
            filter(setReqRespTemplates());

    public static RequestSpecification loginReqSpec = with().
            spec(baseLoginReqSpec).
            contentType(JSON).
            log().uri().log().body();

    public static ResponseSpecification loginRespSpec = with().
            expect().
            statusCode(200).
            contentType(ContentType.JSON).log().all();
}
