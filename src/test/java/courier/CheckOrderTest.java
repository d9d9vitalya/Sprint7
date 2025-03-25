package courier;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;


import static courier.CreateOrderZeroColorTest.trackId1;
import static courier.LoginCourierTest.courierId;
import static io.restassured.RestAssured.given;

public class CheckOrderTest {


    public static int orderId;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void CheckOrderTest() {
        if (trackId1 == 0) {
            throw new IllegalStateException("Courier ID not initialized. Ensure ccc test passed first.");
        }

        // Отправляем запрос на удаление курьера, используя его ID
        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .when()
                        .get("/api/v1/orders/track?t=" + trackId1);  // Подставляем ID в URL

        response.then().statusCode(200);

        System.out.println("Получены данные заказа " + response.getBody().asString());

        orderId = response.jsonPath().getInt("order.id");
    }
}