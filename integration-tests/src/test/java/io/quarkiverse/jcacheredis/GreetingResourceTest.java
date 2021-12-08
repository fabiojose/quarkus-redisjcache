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

}
