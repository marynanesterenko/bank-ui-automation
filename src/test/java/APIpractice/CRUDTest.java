package APIpractice;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.Test;

public class CRUDTest {

    private static final Faker FAKER = new Faker();
    private static final String APIHOST = "https://gorest.co.in/public";
    private static final String APIV = "/v2";
    private static final String AUTH = "3443366bf7b323f317f245944e21ad1a53d6941839ae35e48b99ebda7062a66b";

    @Test
    public void getUsersTest() {
        RestAssured.baseURI = APIHOST + APIV; // this line will need to be in each method
        Response responseListUsers = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + AUTH)
                .accept(ContentType.JSON)
                .when()
                .get("/users");

        // TIP: we do not have to put "Assert" in the beginning, because we imported the Assert statically
        // .getBody() - gives us the entire body of the Response, not just one Object in it (in case if we are retrieving the list of all users)
        // this is an example of "soft assert", which ensures that each assertion will run, regardless whether the first one fails or not
        assertAll(
                () -> assertEquals(200, responseListUsers.getStatusCode(), "Status Codes are not the same"), // here we are verifying whether the Status Code is correct
                () -> assertTrue(responseListUsers.getBody().asString().contains("id"), "id key is not present in the body"), // here we are verifying if the "id" keyword is present in the body
                () -> assertTrue(responseListUsers.getBody().asString().contains("name"), "name key is not present in the body"),
                () -> assertTrue(responseListUsers.getBody().asString().contains("email"), "email key is not present in the body"),
                () -> assertTrue(responseListUsers.getBody().asString().contains("status"), "status key is not present in the body"),
                () -> assertTrue(responseListUsers.getBody().asString().contains("gender"), "gender key is not present in the body")
        );
    }

    @Test
    public void createUserTest() {
        RestAssured.baseURI = APIHOST + APIV;

        // !REMEMBER: whenever we are creating a new Object - we have to ensure we have the body

        String name = FAKER.name().fullName(); // this we can use in case we need to Assert that the correct name was created
        String email = FAKER.internet().emailAddress();
        String body = "{\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"email\": \"" + email + "\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"status\": \"active\"\n" +
                "}";
        // creating a user:
        Response responseCreateUser = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer" + AUTH)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .post("/users");

        System.out.println(responseCreateUser.asString());
        System.out.println(responseCreateUser.getStatusCode());

        // getting the newly created user:
        Response responseGetUserById = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer" + AUTH)
                .accept(ContentType.JSON)
                .when()
                .pathParam("userId", responseCreateUser.jsonPath().getString("id"))
                .get("/users/{userId}");

        System.out.println(responseGetUserById.asString());
        System.out.println(responseGetUserById.getStatusCode());

        assertAll(
                () -> assertEquals(201, responseCreateUser.getStatusCode(), "Status Codes are not the same"), // here we are again verifying the Status Code, EXPECTED: 201
                () -> assertEquals(name, responseCreateUser.jsonPath().getString("name"), "name key is not present in the body"), // jsonPath.getString() -> gets value using a key from the JSON Response
                () -> assertEquals(email, responseCreateUser.jsonPath().getString("email"), "email key is not present in the body"),
                () -> assertEquals(email, responseCreateUser.jsonPath().getString("email"), "email key is not present in the body"),
                () -> assertEquals(email, responseCreateUser.jsonPath().getString("email"), "email key is not present in the body"),
        );
    }
}
