package courier;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class LoginWrongLoginCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void LoginWrongLoginCourierTest() {
        // Отправляем запрос с данными для авторизации
        JsonLoginPassword JsonLoginPassoword = new JsonLoginPassword("123Login", "Password!");

        // Отправляем запрос и получаем ответ
        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .and()
                        .body(JsonLoginPassoword)
                        .when()
                        .post("/api/v1/courier/login");

        // Проверяем статус код 404
        response.then().statusCode(404);

        System.out.println(response.getBody().asString());

    }

}