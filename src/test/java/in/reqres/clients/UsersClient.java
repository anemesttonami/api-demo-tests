package in.reqres.clients;

import in.reqres.models.User;
import in.reqres.models.Users;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static in.reqres.utils.AllureFormatter.setReqRespTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class UsersClient {

    @Step("Запрашиваем список пользователей.")
    public Users getUsersList() {
        return given().basePath("/api/users").
                header("x-api-key", "reqres-free-v1").
                filter(setReqRespTemplates()).
                queryParam("page", 2)
                .log()
                .uri().
                when().
                get().
                then().
                statusCode(200).
                extract().
                as(Users.class);
    }

    @Step("Создаем пользователя.")
    public User createUser(User user) {
        return given().
                basePath("/api/users").
                header("x-api-key", "reqres-free-v1").
                filter(setReqRespTemplates()).
                contentType(JSON).
                body(user).
                log().
                uri().
                log().
                body().
                when().
                post().
                then().
                statusCode(201).
                extract().
                as(User.class);
    }

    @Step("Удаляем пользователя id = {0}.")
    public void deleteUser(int userId) {
        given().
                basePath("/api/users").
                header("x-api-key", "reqres-free-v1").
                filter(setReqRespTemplates()).
                pathParam("userId", userId).
                log().
                uri().
                when().
                delete("{userId}").
                then().
                statusCode(204);
    }

    @Step("Получить ответ с задержкой.")
    public ValidatableResponse getDelayedResponse(int delayValueInSec) {
        return given().
                basePath("/api/users").
                queryParam("delay", delayValueInSec).
                header("x-api-key", "reqres-free-v1").
                filter(setReqRespTemplates()).
                log().
                uri().
                when().
                get().
                then().
                statusCode(200).log().body();
    }
}