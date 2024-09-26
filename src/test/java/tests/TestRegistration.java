package tests;

import core.Registration;
import core.Registrationsuccess;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestRegistration {
    private static String URL = "https://reqres.in/api/register";
    private static String EMAIL = "eve.holt@reqres.in";
    private static String WRONGEMAIL = "eve.holt@reqres.ink1nk1n";
    private static String PASSWORD = "pistol";
    private static String WRONGPASSWORD = "pistol1";

    @Test
    public void testRegistration() {
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Registration user = new Registration(EMAIL, PASSWORD);
        Response response = given()
                .body(user)
                .when()
                .contentType(ContentType.JSON)
                .post(URL);
        Assertions.assertEquals(200, response.statusCode());
        Registrationsuccess registrationsuccess = response.then().log().all()
                .extract().as(Registrationsuccess.class);
        Assertions.assertNotNull(registrationsuccess.getId());
        Assertions.assertNotNull(registrationsuccess.getToken());
        Assertions.assertEquals(id, registrationsuccess.getId());
        Assertions.assertEquals(token, registrationsuccess.getToken());


    }

    @Test
    public void testRegistrationWithError() {
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Registration user = new Registration(WRONGEMAIL, PASSWORD);
        Response response = given()
                .body(user)
                .when()
                .contentType(ContentType.JSON)
                .post(URL);
        Assertions.assertEquals(400, response.statusCode());


    }
}
