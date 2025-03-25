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
public class CreateOrderZeroColorTest {

    public static int trackId1;

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;  // metroStation теперь строка
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;

    public CreateOrderZeroColorTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Test0", "Starikova", "Omsk", "2", "+7 800 111 11 11", 3, "2024-07-07", "0color"}
        });
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/"; // Настройка базового URL
    }

    @Test
    public void CreateOrderZeroColorTest() {
        // Формируем тело запроса с учетом того, что metroStation - строка
        String payload = String.format(
                "{\"firstName\": \"%s\", \"lastName\": \"%s\", \"address\": \"%s\", \"metroStation\": \"%s\", \"phone\": \"%s\", \"rentTime\": %d, \"deliveryDate\": \"%s\", \"comment\": \"%s\"}",
                firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment);

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
        System.out.println("Создан заказ без параметра COLOR, Трек номер: " + response.getBody().asString());

        trackId1 = response.jsonPath().getInt("track");
    }
}