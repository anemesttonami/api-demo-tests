package in.reqres.specs;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static in.reqres.utils.AllureFormatter.setReqRespTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class UsersSpec {

    public static RequestSpecification baseReqSpec = with().
            baseUri("https://reqres.in").
            basePath("/api/users").
            header("x-api-key", "reqres-free-v1").
            filter(setReqRespTemplates()).
            log().
            uri();

    public static RequestSpecification createUserReqSpec = with().
            spec(baseReqSpec).
            contentType(JSON).
            log().
            body();

    public static ResponseSpecification usersListRespSpec = with().
            expect().
            statusCode(200).
            contentType(ContentType.JSON).
            log().all();

    public static ResponseSpecification createUserRespSpec = with().
            expect().
            statusCode(201).
            contentType(ContentType.JSON).
            log().all();
}
