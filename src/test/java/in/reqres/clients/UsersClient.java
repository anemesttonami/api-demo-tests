package in.reqres.clients;

import in.reqres.models.User;
import in.reqres.models.Users;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static in.reqres.specs.UsersSpecifications.*;
import static io.restassured.RestAssured.given;

public class UsersClient {

    @Step("Запрашиваем список пользователей.")
    public Users getUsersList() {
        return given().
                spec(getBaseReqSpec).
                queryParam("page", 2).
                when().
                get().
                then().
                spec(getUsersListRespSpec).
                extract().as(Users.class);
    }

    @Step("Создаем пользователя.")
    public User createUser(User user) {
        return given().
                spec(getCreateUserReqSpec).
                body(user).
                when().
                post().
                then().
                spec(getCreateUserRespSpec).
                extract().as(User.class);
    }

    @Step("Удаляем пользователя id = {0}.")
    public void deleteUser(int userId) {
        given().
                spec(getBaseReqSpec).
                pathParam("userId", userId).
                when().
                delete("{userId}").
                then().
                statusCode(204);
    }

    @Step("Получить ответ с задержкой.")
    public ValidatableResponse getDelayedUsersListResponse(int delayValueInSec) {
        return given().
                spec(getBaseReqSpec).
                queryParam("delay", delayValueInSec).
                when().
                get().
                then().
                spec(getUsersListRespSpec);
    }
}