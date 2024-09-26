package tests;

import core.LoginRequest;
import core.LoginUnSuccessResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestLoginUnSuccess {
    private static String URL = "https://reqres.in/api/login";
    private static String EMAIL = "peter@klaven";
    @Test
    public void testLoginUnSuccess(){
        String error = "Missing password";
        LoginRequest login = new LoginRequest(EMAIL,"");
        Response response = given()
                .body(login)
                .when()
                .contentType(ContentType.JSON)
                .post(URL);
        Assertions.assertEquals(400, response.statusCode());
        LoginUnSuccessResponse loginUnSuccessResponse = response.then().log().all()
                .extract().as(LoginUnSuccessResponse.class);
        Assertions.assertNotNull(loginUnSuccessResponse.getError());
        Assertions.assertEquals(error, loginUnSuccessResponse.getError());



    }


}
