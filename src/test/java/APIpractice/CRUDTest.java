package APIpractice;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CRUDTest extends RESTBase {
    @Test
    public void getUsersTest() {
        // Response interface is where we store our response results
        // * status code, * body, * headers
        Response responseListUsers = restClient.getUsers(AUTH);

        // Assert your tests
        assertAll(
                () -> assertEquals(200, responseListUsers.getStatusCode(), "Status codes are not the same"),
                () -> assertTrue(responseListUsers.getBody().asString().contains("id"), "id key is not present in the response body"),
                () -> assertTrue(responseListUsers.getBody().asString().contains("name"), "name key is not present in the response body"),
                () -> assertTrue(responseListUsers.getBody().asString().contains("email"), "email key is not present in the response body"),
                () -> assertTrue(responseListUsers.getBody().asString().contains("status"), "status key is not present in the response body"),
                () -> assertTrue(responseListUsers.getBody().asString().contains("gender"), "gender key is not present in the response body")
        );
    }

    @Test
    public void createUserTest() {
        // Preparing request body
        String name = FAKER.name().fullName();
        String email = FAKER.internet().emailAddress();
        String body = "{\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"email\": \"" + email + "\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"status\": \"inactive\"\n" +
                "}";

        // Save post response to variable
        // We will need status code of response
        // We will need body of response
        Response responseCreateUser = restClient.createUser(AUTH, body);

        // Assumptions to find issues with your implementation
        // Assertions to find issues with the application under test
        Assumptions.assumeTrue(responseCreateUser.getStatusCode() == 201, "Create user didn't return 201 status code");

        // Save get response in variable
        // We need it to make sure 100% that our user has been created and saved to DB
        // We need status code
        // We need body of response
        Response responseGetUserById = restClient.getUserById(AUTH, responseCreateUser.jsonPath().getString("id"));

        // Assert
        assertAll(
                // assert 201 for create
                () -> assertEquals(201, responseCreateUser.getStatusCode(), "Status codes are not the same"),
                // jsonPath.getString() -> gets value using a key from the json response
                () -> assertEquals(name, responseCreateUser.jsonPath().getString("name"), "Names are not the same"),
                // assert that name and email are correct in post response body
                () -> assertEquals(email, responseCreateUser.jsonPath().getString("email"), "Emails are not the same"),
                // assert 200 for get
                () -> assertEquals(200, responseGetUserById.getStatusCode(), "Status codes are not the same"),
                // assert name and email and keys in get response
                () -> assertEquals(name, responseGetUserById.jsonPath().getString("name"), "Names are not the same"),
                () -> assertEquals(email, responseGetUserById.jsonPath().getString("email"), "Emails are not the same"),
                () -> assertTrue(responseGetUserById.getBody().asString().contains("id"), "id key is not present in the response body"),
                () -> assertTrue(responseGetUserById.getBody().asString().contains("name"), "name key is not present in the response body"),
                () -> assertTrue(responseGetUserById.getBody().asString().contains("email"), "email key is not present in the response body"),
                () -> assertTrue(responseGetUserById.getBody().asString().contains("status"), "status key is not present in the response body"),
                () -> assertTrue(responseGetUserById.getBody().asString().contains("gender"), "gender key is not present in the response body")
        );
    }

    // How to test delete user and make it independent
    // create user +
    // get user, validate he exists +
    // delete user +
    // get user, validate he doesn't exist +
    // Assertions
    // In the hooks, you have before and after
    // before test you can create user
    // hook clean data after you, delete user and the history

    @Test
    public void deleteUserTest() {
        // Preparing request body
        String name = FAKER.name().fullName();
        String email = FAKER.internet().emailAddress();
        String body = "{\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"email\": \"" + email + "\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"status\": \"inactive\"\n" +
                "}";

        // Save post response to variable
        // We will need status code of response
        // We will need body of response
        Response responseCreateUser = restClient.createUser(AUTH, body);

        String userId = responseCreateUser.jsonPath().getString("id");

        // Assumptions to find issues with your implementation
        // Assertions to find issues with the application under test
        Assumptions.assumeTrue(responseCreateUser.getStatusCode() == 201, "Create user didn't return 201 status code");

        // Save get response in variable
        // We need it to make sure 100% that our user has been created and saved to DB
        // We need status code
        // We need body of response
        Response responseGetUserById = restClient.getUserById(AUTH, userId);

        Assumptions.assumeTrue(responseGetUserById.getStatusCode() == 200, "Get user didn't return 200 status code");

        // Save delete response in variable
        Response responseDeleteUser = restClient.deleteUserById(AUTH, userId);

        Assumptions.assumeTrue(responseDeleteUser.getStatusCode() == 204, "Delete user didn't return 204 status code");

        // Save get response after delete
        Response responseGetUserByIdAfterDelete = restClient.getUserById(AUTH, userId);

        // Assert
        assertAll(
                () -> assertEquals(204, responseDeleteUser.getStatusCode(), "Status codes are not the same"),
                () -> assertEquals(404, responseGetUserByIdAfterDelete.getStatusCode(), "Status codes are not the same"),
                () -> assertEquals("Resource not found", responseGetUserByIdAfterDelete.jsonPath().getString("message"), "Message key is not present in the response with status code 404")
        );
    }

    // Create user +
    // + Get user, validate he exists
    // + Update user
    // + Get user
    // + Assert that new data is saved

    @Test
    public void updateUserTest() {
        // Preparing request body
        String name = FAKER.name().fullName();
        String email = FAKER.internet().emailAddress();
        String body = "{\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"email\": \"" + email + "\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"status\": \"inactive\"\n" +
                "}";

        // Save post response to variable
        // We will need status code of response
        // We will need body of response
        Response responseCreateUser = restClient.createUser(AUTH, body);

        String userId = responseCreateUser.jsonPath().getString("id");

        // Assumptions to find issues with your implementation
        // Assertions to find issues with the application under test
        Assumptions.assumeTrue(responseCreateUser.getStatusCode() == 201, "Create user didn't return 201 status code");

        // Save get response in variable
        // We need it to make sure 100% that our user has been created and saved to DB
        // We need status code
        // We need body of response
        Response responseGetUserById = restClient.getUserById(AUTH, userId);

        Assumptions.assumeTrue(responseGetUserById.getStatusCode() == 200, "Get user didn't return 200 status code");

        String nameUpdated = FAKER.name().fullName();
        String emailUpdated = FAKER.internet().emailAddress();
        String bodyUpdate = "{\n" +
                "    \"name\": \"" + nameUpdated + "\",\n" +
                "    \"email\": \"" + emailUpdated + "\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"status\": \"active\"\n" +
                "}";

        // Saved put request to the response variable
        Response responseUpdateUserById = restClient.updateUserById(AUTH, bodyUpdate, userId);

        Assumptions.assumeTrue(responseUpdateUserById.getStatusCode() == 200, "Update user didn't return 200 status code");

        // Save get response after update
        Response responseGetUserByIdAfterUpdate = restClient.getUserById(AUTH, userId);

        // Assert
        assertAll(
                () -> assertEquals(200, responseUpdateUserById.getStatusCode(), "Status codes are not the same"),
                () -> assertEquals(nameUpdated, responseUpdateUserById.jsonPath().getString("name"), "Names are not the same"),
                () -> assertEquals(emailUpdated, responseUpdateUserById.jsonPath().getString("email"), "Emails are not the same"),
                () -> assertEquals("active", responseUpdateUserById.jsonPath().getString("status"), "Statuses are not the same"),
                () -> assertEquals(200, responseGetUserByIdAfterUpdate.getStatusCode(), "Status codes are not the same"),
                () -> assertEquals(nameUpdated, responseGetUserByIdAfterUpdate.jsonPath().getString("name"), "Names are not the same"),
                () -> assertEquals(emailUpdated, responseGetUserByIdAfterUpdate.jsonPath().getString("email"), "Emails are not the same"),
                () -> assertEquals("active", responseGetUserByIdAfterUpdate.jsonPath().getString("status"), "Statuses are not the same")
        );
    }

}
