package in.reqres.tests;

import in.reqres.clients.UsersClient;
import in.reqres.models.ResponseData;
import in.reqres.models.User;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static in.reqres.utils.TestHelper.isTimeDiffNotMoreThanTenSeconds;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("User API")
public class UsersTests {

    private static final UsersClient CLIENT = new UsersClient();

    @Test
    @Tag("smoke")
    @DisplayName("Получить список пользователей и проверить max id.")
    @Description("Проверяем, что список пользователей может быть получен и максимальный id в списке = 12.")
    void maxIdInUsersListIsCorrect() {
        int maxId = CLIENT.getUsersList().getData()
                .stream().map(ResponseData::getId).max(Integer::compareTo).get();

        Assertions.assertEquals(12, maxId, "Максимальный id пользователя не равен 12.");
    }

    @Test
    @Tag("smoke")
    @DisplayName("Создание пользователя и генерация времени создания пользователя.")
    @Description("Проверяем, что пользователь создаётся и время с точностью до секунд генерируется корректно.")
    void createUserAndCheckCreationTime() {
        User user = new User();
        user.setName("morpheus");
        user.setJob("leader");

        User createdUser = CLIENT.createUser(user);

        Assertions.assertTrue(isTimeDiffNotMoreThanTenSeconds(createdUser.getCreatedAt()));
    }

    @Test
    @Tag("smoke")
    @DisplayName("Удаление пользователя работает.")
    void isDeleteUserWorking() {
        CLIENT.deleteUser(2);
    }

    @Test
    @Tag("regress")
    @DisplayName("Различные проверки для эндпоинта, отдающего ответ с задержкой.")
    void differentVariantsChecksOfDelayedResponse() {
        ValidatableResponse validatableResponse = CLIENT.getDelayedUsersListResponse(3);

        Response response = validatableResponse.extract().response();

        List<Map<String, String>> users = response.jsonPath().getList("data");

        step("Проверяем, что _meta.features содержит \"Data Persistence\" и \"Real-time Analytics\".", () ->
                validatableResponse.body("_meta.features", hasItems("Data Persistence", "Real-time Analytics")));

        step("Проверяем, что первый объект \"_meta\" содержит подстроку \"Second\".", () ->
                validatableResponse.body("_meta.features[0]", containsString("Second")));

        step("Проверяем, что в \"data\" есть объект с id = 3 и \"last_name\" такого объекта = \"Wong\".", () ->
                validatableResponse.body("data.find { it.id == 3 }.last_name", is("Wong")));

        step("Проверяем, что в \"data\" у каждого объекта длина \"email\" больше 11 символов и"
                + " каждый \"email\" оканчивается на \"@reqres.in\".", () ->
                validatableResponse.body("data.every { it.email.length() > 11 && it.email.endsWith('@reqres.in') }", is(true)));

        step("Проверяем, что среди объектов \"data\" есть такой, у которого \"email\" = \"janet.weaver@reqres.in\".",
                () -> assertThat(users)
                        .hasSize(6)
                        .extracting("email")
                        .contains("janet.weaver@reqres.in"));
    }
}
