package in.reqres.tests;

import in.reqres.clients.LoginClient;
import io.qameta.allure.Epic;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.qameta.allure.Allure.step;

@Epic("Login API")
public class LoginTests extends BaseTest {

    private static final LoginClient CLIENT = new LoginClient();
    private static final String ENDPOINT = "/login";

    @Test
    @Tag("smoke")
    @DisplayName("Авторизация и создание токена.")
    void loginCheck() {
        Map<String, String> data = new HashMap<>();
        data.put("email", "eve.holt@reqres.in");
        data.put("password", "cityslicka");

        step("Логинимся и проверяем корректность сгенерированного токена.", () ->
                Assertions
                        .assertThat((String) CLIENT.login(data, ENDPOINT).jsonPath().get("token"))
                        .as("Токен отсутствует.")
                        .isNotEmpty()
                        .as("Токен должен быть алфанамерическим и содержать минимум 14 символов.")
                        .matches("[A-Za-z0-9]{14,}")
                        .as("Полученный токен отличается от ожидаемого.")
                        .isEqualTo("QpwL5tke4Pnpja7X4"));
    }
}
