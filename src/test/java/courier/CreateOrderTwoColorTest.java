package courier;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTwoColorTest {

    public static int trackId3;

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String color;

    public CreateOrderTwoColorTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Test2", "Starikova", "Omsk", "2", "+7 800 111 11 11", 3, "2024-07-07", "2color", "[\"BLACK\", \"GREY\"]"}
        });
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/"; // Настройка базового URL
    }

    @Test
    public void CreateOrderTwoColorTest() {
        // Формируем тело запроса с добавленным массивом для "color"
        String payload = String.format(
                "{\"firstName\": \"%s\", \"lastName\": \"%s\", \"address\": \"%s\", \"metroStation\": \"%s\", \"phone\": \"%s\", \"rentTime\": %d, \"deliveryDate\": \"%s\", \"comment\": \"%s\", \"color\": %s}",
                firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);

        // Отправка запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post("/api/v1/orders")  // Путь для создания заказа
                .then()
                .statusCode(201)  // Ожидаемый статус-код ответа
                .body("track", notNullValue())  // Проверка, что поле "track" присутствует
                .extract().response();

        // Выводим ответ
        //System.out.println("Request Payload: " + payload);
        //System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Создан заказ с двумя Цветами, Трек номер: " + response.getBody().asString());

        trackId3 = response.jsonPath().getInt("track");
    }
}