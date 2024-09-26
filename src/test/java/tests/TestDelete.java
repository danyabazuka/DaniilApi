package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestDelete {
    private static String URL = "https://reqres.in/api/users/2";
    @Test
    public void testDelete(){
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .delete(URL);
        Assertions.assertEquals(200, response.statusCode());
    }


}
