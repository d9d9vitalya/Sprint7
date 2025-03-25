package courier;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static courier.LoginCourierTest.courierId;
import static io.restassured.RestAssured.given;

public class DeleteCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void DeleteCourier() {
        // Убедимся, что ID курьера был получен в тесте ccc
        if (courierId == 0) {
            throw new IllegalStateException("Courier ID not initialized. Ensure ccc test passed first.");
        }

        // Отправляем запрос на удаление курьера, используя его ID
        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .when()
                        .delete("/api/v1/courier/" + courierId);  // Подставляем ID в URL

        response.then().statusCode(200);
        System.out.println("Курьер удален" + response.getBody().asString());
    }
}

