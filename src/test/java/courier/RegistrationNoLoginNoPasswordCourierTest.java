package courier;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RegistrationNoLoginNoPasswordCourierTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void RegistrationCourierTest() {
        // Создаем объект с данными для нового курьера
        File json = new File("src/NoLoginPassword.json");

        // Отправляем запрос и получаем ответ
        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/courier");

        // Выводим тело ответа в консоль
        System.out.println("Нужно заполнить все обязательные поля");
        System.out.println("Response Body: " + response.getBody().asString());

        // Проверяем статус код и ответ
        response.then()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));


    }
}




