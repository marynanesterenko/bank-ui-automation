package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// this Class will hold all our methods that we can reuse
public class RESTClient {

    // Save post response to variable
    // We will need status code of response
    // We will need body of response
    public Response createUser(String AUTH, String jsonBody) {
        return RestAssured
                // Arrange part
                .given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + AUTH)
                .accept(ContentType.JSON)
                // We provide the request body to the .body()
                // .body() accepts String and converts it to JSON
                .body(jsonBody)
                // Act part
                .when()
                // Use post method to create new UsersPojo in /users ep
                .post("/users");
    }

    // Save get response in variable
    // We need it to make sure 100% that our UsersPojo has been created and saved to DB
    // We need status code
    // We need body of response
    public Response getUserById(String AUTH, String userId) {
        return RestAssured
                // Arrange
                .given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + AUTH)
                .accept(ContentType.JSON)
                // Act
                .when()
                // responseCreateUser.jsonPath().getString("id") -> get id from post response
                .pathParam("userId", userId)
                // get UsersPojo by id, userId is pathparam where we dynamically provide id of newly created UsersPojo
                .get("/users/{userId}");
    }

    // Response interface is where we store our response results
    // * status code, * body, * headers
    public Response getUsers(String AUTH) {
        return RestAssured
                // Arrange our tests
                .given()
                // we provide data type for body we send in contentType
                .contentType(ContentType.JSON)
                // setup headers, auth keys in headers
                .headers("Authorization", "Bearer " + AUTH)
                // we provide data type for body we want to receive in accept
                .accept(ContentType.JSON)
                // Act of tests
                .when()
                // Send request with get method to endpoint /users
                // BaseURL gets attached at the beginning of /
                .get("/users");
    }

    // Save delete response in variable
    public Response deleteUserById(String AUTH, String userId) {
        return RestAssured
                // Arrange
                .given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + AUTH)
                .accept(ContentType.JSON)
                // Act
                .when()
                // Provide id of UsersPojo you want to delete
                .pathParam("userId", userId)
                // Delete method is used
                .delete("/users/{userId}");
    }

    // Saved put request to the response variable
    public Response updateUserById(String AUTH, String body, String userId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + AUTH)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .pathParam("userId", userId)
                .put("/users/{userId}");
    }
}
