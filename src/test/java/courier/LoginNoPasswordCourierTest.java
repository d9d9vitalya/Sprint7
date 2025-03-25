package courier;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class LoginNoPasswordCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void LoginNoPasswordCourierTest() {
        // Отправляем запрос с данными для авторизации
        JsonLoginPassword JsonLoginPassoword = new JsonLoginPassword("123Login098", "");

        // Отправляем запрос и получаем ответ
        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .and()
                        .body(JsonLoginPassoword)
                        .when()
                        .post("/api/v1/courier/login");

        //Проверяем статус код 400 и текст
        response.then()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));

        System.out.println(response.getBody().asString());

    }

}