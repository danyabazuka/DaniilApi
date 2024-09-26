package tests;

import core.Register;
import core.RegisterUnSuccess;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestRegUnSuccess {
    private static String URL = "https://reqres.in/api/register";
    private static String EMAIL = "sydney@fife";
    private static String WRONGEMAIL = "123";

    @Test
    public void testRegUnSuccess(){
        String error = "Missing password";
        Register register = new Register(EMAIL);
        Response response = given()
                .body(register)
                .when()
                .contentType(ContentType.JSON)
                .post(URL);
        Assertions.assertEquals(400, response.statusCode());
        RegisterUnSuccess registerUnSuccess = response.then().log().all()
                .extract().as(RegisterUnSuccess.class);
        Assertions.assertNotNull(registerUnSuccess.getError());
        Assertions.assertEquals(error,registerUnSuccess.getError());

    }
    @Test
    public void testRegUnSuccessWithError(){
        String error = "Missing password";
        Register register = new Register(WRONGEMAIL);
        Response response = given()
                .body(register)
                .when()
                .contentType(ContentType.JSON)
                .post(URL);
        Assertions.assertEquals(400, response.statusCode());
        RegisterUnSuccess registerUnSuccess = response.then().log().all()
                .extract().as(RegisterUnSuccess.class);
        Assertions.assertNotNull(registerUnSuccess.getError());
        Assertions.assertEquals(error,registerUnSuccess.getError());

    }
}
