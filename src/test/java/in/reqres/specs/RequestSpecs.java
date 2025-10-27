package in.reqres.specs;

import io.restassured.specification.RequestSpecification;

import static in.reqres.utils.AllureFormatter.setReqRespTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class RequestSpecs {
    public static RequestSpecification baseUserReqSpec = with().
            baseUri("https://reqres.in").
            basePath("/api/users").
            header("x-api-key", "reqres-free-v1").
            filter(setReqRespTemplates()).
            log().all();

    public static RequestSpecification createUserReqSpec = with().
            spec(baseUserReqSpec).
            contentType(JSON).
            log().
            body();

    public static RequestSpecification baseLoginReqSpec = with().
            baseUri("https://reqres.in").
            basePath("/api/login").
            header("x-api-key", "reqres-free-v1").
            filter(setReqRespTemplates()).
            log().all();

    public static RequestSpecification loginReqSpec = with().
            spec(baseLoginReqSpec).
            contentType(JSON);
}
