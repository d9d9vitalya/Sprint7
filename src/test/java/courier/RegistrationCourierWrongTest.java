package courier;

import io.restassured.response.Response;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RegistrationCourierWrongTest {
    @Test
    public void WrongRegistrationCourierTest() {
        File json = new File("src/LoginPassword.json");

        // Отправляем запрос и получаем ответ
        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/courier");

        // Выводим тело ответа в консоль
        System.out.println("Response Body: " + response.getBody().asString());

        // Проверяем статус код и тело ответа
        response.then()
                .statusCode(409)  // Ожидаем код 409 Conflict
                .body("code", equalTo(409))
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

}
