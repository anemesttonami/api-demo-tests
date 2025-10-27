package in.reqres.specs;

import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class ResponseSpecs {

    public static ResponseSpecification baseRespSpec = with().
            expect().
            statusCode(200).
            contentType(ContentType.JSON).log().all();

    public static ResponseSpecification loginRespSpec = with().
            expect().
            spec(baseRespSpec);

    public static ResponseSpecification usersListRespSpec = with().
            expect().
            spec(baseRespSpec);

    public static ResponseSpecification createUserRespSpec = with().
            expect().
            statusCode(201).
            contentType(ContentType.JSON).log().all();
}
