package courier;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RegistrationCourierTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void RegistrationCourierTest() {
        // Создаем объект с данными для нового курьера
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
        System.out.println("Регистрация произведена");
        System.out.println("Response Body: " + response.getBody().asString());

        // Проверяем статус код и что ответ содержит ok: true
        response.then()
                .statusCode(201)
                .assertThat()
                .body("ok", equalTo(true));


    }
}




