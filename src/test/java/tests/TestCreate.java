package tests;

import core.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;

import static io.restassured.RestAssured.given;

public class TestCreate {
    private static String URL = "https://reqres.in/api/users";
    private static String NAME = "morpheus";
    private static String JOB = "leader";

    @Test
    public void testCreate(){
        String name = "morpheus";
        String job = "leader";
        String regex = "(.{8})$";
        CreateRq user = new CreateRq(NAME, JOB);
        Response response = given()
                .body(user)
                .when()
                .contentType(ContentType.JSON)
                .post(URL);
        Assertions.assertEquals(201, response.statusCode());
        CreateRs createRs = response.then().log().all()
                .extract().as(CreateRs.class);
        Assertions.assertNotNull(createRs.getName());
        Assertions.assertNotNull(createRs.getJob());
        Assertions.assertNotNull(createRs.getId());
        Assertions.assertNotNull(createRs.getCreatedAt());
        Assertions.assertEquals(name, createRs.getName());
        Assertions.assertEquals(job, createRs.getJob());
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex,"");
        Assertions.assertEquals(currentTime, createRs.getCreatedAt().replaceAll(regex,""));





    }
}
