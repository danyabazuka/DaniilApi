package tests;

import core.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

import static io.restassured.RestAssured.given;

public class TestCreate {
    private static String URL = "https://reqres.in/api/users";
    private static String NAME = "morpheus";
    private static String JOB = "leader";

    @Test
    public void testCreate(){
            String name = "morpheus";
            String job = "leader";

            // Создаем запрос на создание пользователя
            CreateRq user = new CreateRq(name, job);
            Response response = given()
                    .body(user)
                    .when()
                    .contentType(ContentType.JSON)
                    .post(URL);

            // Проверяем статус-код ответа
            Assertions.assertEquals(201, response.statusCode());

            // Извлекаем ответ как объект CreateRs и выводим его в лог
            CreateRs createRs = response.then().log().all()
                    .extract().as(CreateRs.class);

            // Проверяем, что обязательные поля не пусты
            Assertions.assertNotNull(createRs.getName());
            Assertions.assertNotNull(createRs.getJob());
            Assertions.assertNotNull(createRs.getId());
            Assertions.assertNotNull(createRs.getCreatedAt());

            // Проверяем, что имя и должность совпадают с теми, которые были в запросе
            Assertions.assertEquals(name, createRs.getName());
            Assertions.assertEquals(job, createRs.getJob());

            // Получаем текущее время и время из ответа API
            Instant currentTime = Clock.systemUTC().instant();
            Instant createdAtTime = Instant.parse(createRs.getCreatedAt());

            // Допустимая разница в 5 секунд
            long allowedDifference = 5000;
            long difference = Duration.between(currentTime, createdAtTime).toMillis();

            // Проверяем, что разница во времени не превышает допустимую
            Assertions.assertTrue(Math.abs(difference) < allowedDifference,
                    "Разница во времени слишком велика: " + difference + " мс");
        }
    }
