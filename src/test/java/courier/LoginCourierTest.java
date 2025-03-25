package courier;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class LoginCourierTest {

    public static int courierId;  // Статическое поле для хранения ID курьера

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void CorrectLoginCourierTest() {
        // Отправляем запрос с данными для авторизации
        JsonLoginPassword JsonLoginPassoword = new JsonLoginPassword("123Login098", "Password!");

        // Отправляем запрос и получаем ответ
        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .and()
                        .body(JsonLoginPassoword)
                        .when()
                        .post("/api/v1/courier/login");

        // Проверяем статус код 200
        response.then().statusCode(200);

        // Извлекаем ID курьера из ответа
        courierId = response.jsonPath().getInt("id");

        System.out.println("Авторизация произведена");
        System.out.println("Courier ID: " + courierId);
    }

}