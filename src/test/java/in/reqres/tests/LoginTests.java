package in.reqres.tests;

import in.reqres.clients.LoginClient;
import io.qameta.allure.Epic;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.qameta.allure.Allure.step;

@Epic("Login API")
public class LoginTests {

    private static final LoginClient CLIENT = new LoginClient();

    @Test
    @Tag("smoke")
    @DisplayName("Авторизация.")
    void loginCheck(){
        Map<String,String> data = new HashMap<>();
        data.put("email","eve.holt@reqres.in");
        data.put("password","cityslicka");

        step("Логинимся и проверяем корректность сгенерированного токена.", ()->
                Assertions
                        .assertThat((String) CLIENT.login(data).jsonPath().get("token"))
                        .as("Токен отсутствует.")
                        .isNotEmpty()
                        .as("Полученный токен отличается от ожидаемого.")
                        .isEqualTo("QpwL5tke4Pnpja7X4"));
    }
}
