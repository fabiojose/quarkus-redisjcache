package io.quarkiverse.jcacheredis;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello RESTEasy"));
    }

    @Test
    void should_response_the_cached_value() {

        var expected = given()
                .when().get("/hello/ping")
                .as(MyResponse.class);

        given()
                .when().get("/hello/ping")
                .then()
                .statusCode(200)
                .body("pong", is(expected.getPong()));

    }

    @Test
    void should_reponse_the_cached_value_with_dynamic_cache_key() {

        var expected = given()
                .when().get("/hello/term?name=UnitTest")
                .as(Found.class);

        given()
                .when().get("/hello/term?name=UnitTest")
                .then()
                .statusCode(200)
                .body("term", is(expected.getTerm()));
    }

    @Test
    void should_reponse_the_cached_value_with_two_cache_key() {

        var expected = given()
                .when().get("/hello/sort?name=Sorting&sort=ASC")
                .as(Found.class);

        given()
                .when().get("/hello/sort?name=Sorting&sort=ASC")
                .then()
                .statusCode(200)
                .body("term", is(expected.getTerm()));
    }
}
