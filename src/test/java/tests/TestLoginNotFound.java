package tests;

import core.LoginNotFound;
import core.LoginRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestLoginNotFound {
    private static String URL = "https://reqres.in/api/login";
    private static String EMAIL = "eve.holt@reqres.in";
    private static String WRONGEMAIL = "eve.2dfggholt@reqres.in";
    private static String PASSWORD = "cityslicka";
    private static String WRONGPASSWORD = "cityslicka2342n";

    @Test
    public void testLoginSuccessWithError() {
        String error = "user not found";
        LoginRequest login = new LoginRequest(WRONGEMAIL, WRONGPASSWORD);
        Response response = given()
                .body(login)
                .when()
                .contentType(ContentType.JSON)
                .post(URL);
        Assertions.assertEquals(400, response.statusCode());
        LoginNotFound loginNotFound = response.then().log().all()
                .extract().as(LoginNotFound.class);
        Assertions.assertNotNull(loginNotFound.getError());
        Assertions.assertEquals(error, loginNotFound.getError());

    }
}
