package tests;

import core.ResourceList;
import core.UserData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TestGetResourceList {
    private static String URL = "https://reqres.in/api/unknown";
    private static String URL1 = "https://reqres.in/api/unknown/2";

    private static String URL2 = "https://reqres.in/api/unknown/23";

    @Test
    public void testGetResourceList() {
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL);
        Assertions.assertEquals(200, response.statusCode());
        List<ResourceList> users = response.then().log().all().extract().body().jsonPath().getList("data", ResourceList.class);

    }

    @Test
    public void testGetResourceListSingle() {
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL1);
        Assertions.assertEquals(200, response.statusCode());
        ResourceList users = response.then().log().all().extract().body().jsonPath().getObject("data", ResourceList.class);
    }
    @Test
    public void testGetResourceListNotFound(){
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL2);
        Assertions.assertEquals(404, response.statusCode());
    }
}
