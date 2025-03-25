package courier;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static courier.LoginCourierTest.courierId;
import static courier.CheckOrderTest.orderId;
import static io.restassured.RestAssured.given;

public class AcceptOrderCourierTest {



    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void acceptOrderTest() {
        // Проверяем, что orderId и courierId инициализированы
        if (orderId == 0 || courierId == 0) {
            throw new IllegalStateException("Order ID or Courier ID not initialized. Ensure create order test and courier creation test passed first.");
        }

        System.out.println("Номер заказа: " + orderId);
        System.out.println("Номер курьера: " + courierId);

        // Отправляем запрос на принятие заказа с параметром courierId в URL
        Response response = given()
                .queryParam("courierId", courierId)
                .put("/api/v1/orders/accept/" + orderId)
                .then()
                .statusCode(200)  // Ожидаемый статус-код ответа
                .extract().response();

        // Выводим тело ответа
        System.out.println("Заказ принят " + response.getBody().asString());

    }
}