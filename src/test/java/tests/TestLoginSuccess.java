package tests;

import core.LoginRequest;
import core.LoginSuccessResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestLoginSuccess {
    private static String URL = "https://reqres.in/api/login";
    private static String EMAIL = "eve.holt@reqres.in";
    private static String WRONGEMAIL = "eve.2dfggholt@reqres.in";
    private static String PASSWORD = "cityslicka";
    private static String WRONGPASSWORD = "cityslicka2342n";

    @Test
    public void testLoginSuccess() {
        String token = "QpwL5tke4Pnpja7X4";
        LoginRequest login = new LoginRequest(EMAIL, PASSWORD);
        Response response = given()
                .body(login)
                .when()
                .contentType(ContentType.JSON)
                .post(URL);
        Assertions.assertEquals(200, response.statusCode());
        LoginSuccessResponse loginSuccessResponse = response.then().log().all()
                .extract().as(LoginSuccessResponse.class);
        Assertions.assertNotNull(loginSuccessResponse.getToken());
        Assertions.assertEquals(token, loginSuccessResponse.getToken());

    }

    @Test
    public void testLoginSuccessWithError() {
        String token = "QpwL5tke4Pnpja7X4";
        LoginRequest login = new LoginRequest(WRONGEMAIL, WRONGPASSWORD);
        Response response = given()
                .body(login)
                .when()
                .contentType(ContentType.JSON)
                .post(URL);
        Assertions.assertEquals(400, response.statusCode());
        LoginSuccessResponse loginSuccessResponse = response.then().log().all()
                .extract().as(LoginSuccessResponse.class);
//        Assertions.assertNotNull(loginSuccessResponse.getToken());
//        Assertions.assertEquals(token, loginSuccessResponse.getToken());

    }
}
