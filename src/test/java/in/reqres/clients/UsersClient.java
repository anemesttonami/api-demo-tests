package in.reqres.clients;

import in.reqres.models.User;
import in.reqres.models.Users;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static in.reqres.specs.RequestSpecs.baseUserReqSpec;
import static in.reqres.specs.RequestSpecs.createUserReqSpec;
import static in.reqres.specs.ResponseSpecs.createUserRespSpec;
import static in.reqres.specs.ResponseSpecs.usersListRespSpec;
import static io.restassured.RestAssured.given;

public class UsersClient {

    @Step("Запрашиваем список пользователей.")
    public Users getUsersList(String endpoint) {
        return given().
                spec(baseUserReqSpec).
                queryParam("page", 2).
                when().
                get(endpoint).
                then().
                spec(usersListRespSpec).
                extract().as(Users.class);
    }

    @Step("Создаем пользователя.")
    public User createUser(User user, String endpoint) {
        return given().
                spec(createUserReqSpec).
                body(user).
                when().
                post(endpoint).
                then().
                spec(createUserRespSpec).
                extract().as(User.class);
    }

    @Step("Удаляем пользователя id = {0}.")
    public void deleteUser(int userId, String endpoint) {
        given().
                spec(baseUserReqSpec).
                pathParam("userId", userId).
                when().
                delete(endpoint + "{userId}").
                then().
                statusCode(204);
    }

    @Step("Получить ответ с задержкой.")
    public ValidatableResponse getDelayedUsersListResponse(int delayValueInSec, String endpoint) {
        return given().
                spec(baseUserReqSpec).
                queryParam("delay", delayValueInSec).
                when().
                get(endpoint).
                then().
                spec(usersListRespSpec);
    }
}