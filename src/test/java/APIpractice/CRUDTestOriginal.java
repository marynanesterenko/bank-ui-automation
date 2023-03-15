//package APIpractice;
//
//import com.github.javafaker.Faker;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertAll;
//
//import org.junit.Test;
//import org.junit.jupiter.api.Assumptions;
//
//public class CRUDTest extends RESTBase {
//
//    // usually these private variables will be provided in the config.properties file
//    private static final Faker FAKER = new Faker();
//    private static final String APIHOST = "https://gorest.co.in/public";
//    private static final String APIV = "/v2";
//    private static final String AUTH = "3443366bf7b323f317f245944e21ad1a53d6941839ae35e48b99ebda7062a66b";
//
//    @Test
//    public void getUsersTest() {
//        // setting up baseURL by adding host to versions of API
//        RestAssured.baseURI = APIHOST + APIV; // this line will need to be in each method
//
//        // Response Interface is where we store our Response results: Status Code, Response Body
//        Response responseListUsers = RestAssured
//                // Arrange our tests
//                .given()
//                // we provide data type for body we send in contentType header
//                .contentType(ContentType.JSON)
//                // setup headers, auth keys in headers
//                .header("Authorization", "Bearer " + AUTH)
//                // we provide data type for body we wat to receive in accept
//                .accept(ContentType.JSON)
//                // acts of tests
//                .when()
//                // send request with get method to endpoint /users
//                // baseUrl gets attached at the beginning of /
//                .get("/users");
//
//        // TIP: we do not have to put "Assert" in the beginning, because we imported the Assert statically in the imports section at the top ^^
//        // .getBody() - gives us the entire body of the Response, not just one Object in it (applies in the case if we are retrieving the list of all users)
//        // below is an example of "soft assert" structure, which ensures that each assertion will run, regardless whether the first one fails or not
//        assertAll(
//                () -> assertEquals(200, responseListUsers.getStatusCode(), "Status Codes are not the same"), // here we are verifying whether the Status Code is correct
//                () -> assertTrue(responseListUsers.getBody().asString().contains("id"), "id key is not present in the body"), // here we are verifying if the "id" keyword is present in the body
//                () -> assertTrue(responseListUsers.getBody().asString().contains("name"), "name key is not present in the body"),
//                () -> assertTrue(responseListUsers.getBody().asString().contains("email"), "email key is not present in the body"),
//                () -> assertTrue(responseListUsers.getBody().asString().contains("status"), "status key is not present in the body"),
//                () -> assertTrue(responseListUsers.getBody().asString().contains("gender"), "gender key is not present in the body")
//        );
//    }
//
//    @Test
//    public void createUserTest() {
//        // setting up baseURL by adding host to versions of API
//        RestAssured.baseURI = APIHOST + APIV;
//
//        // !REMEMBER: whenever we are creating a new Object - we have to ensure we have the body
//
//        // Preparing request body
//        String name = FAKER.name().fullName(); // this we can use in case we need to Assert that the correct name was created
//        String email = FAKER.internet().emailAddress();
//        String body = "{\n" +
//                "    \"name\": \"" + name + "\",\n" +
//                "    \"email\": \"" + email + "\",\n" +
//                "    \"gender\": \"female\",\n" +
//                "    \"status\": \"active\"\n" +
//                "}";
//        // creating a user:
//        // !NOTE: watch out for the white space after the "Bearer " - it MUST be  present there
//        // 1) saving response to a variable, because we will need the Status Code of Response and the body of the Response
//        Response responseCreateUser = RestAssured
//                // Arrange part
//                .given()
//                .contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + AUTH)
//                .accept(ContentType.JSON)
//                // here we provide the Request body to he .body()
//                // .body accepts String and converts it to JSON
//                .body(body)
//                // act part
//                .when()
//                // here we use post method to create nes user in /users endpoint
//                .post("/users");
//
//        // Assumptions are used to check that our steps are actually correct, we need to verify that we do not have any issues in our test steps
//        Assumptions.assumeTrue(responseCreateUser.getStatusCode() == 201, "Create user did not return expected Status Code");
//
//        System.out.println(responseCreateUser.asString());
//        System.out.println(responseCreateUser.getStatusCode());
//
//        // we can also replace the below lines of code with the SELECT SQL query
//        // getting the newly created user:
//        // 1) save the response in variable
//        // 2) we need to make sure 100% that our user has been created and saved in the database
//        Response responseGetUserById = RestAssured
//                // arrange
//                .given()
//                .contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + AUTH)
//                .accept(ContentType.JSON)
//                // act
//                .when()
//                // responseCreateUser.jsonPath().getString("id") -> getting "id" from response
//                .pathParam("userId", responseCreateUser.jsonPath().getString("id"))
//                //get user by id, userid is path param where we dynamically provide id of newly created user
//                .get("/users/{userId}");
//
//        System.out.println(responseGetUserById.asString());
//        System.out.println(responseGetUserById.getStatusCode());
//
//        assertAll(
//                // assert 201 for create
//                () -> assertEquals(201, responseCreateUser.getStatusCode(), "Status Codes are not the same"), // here we are again verifying the Status Code, EXPECTED: 201
//                // jsonPath() -> method helps us to enter the JSON
//                () -> assertEquals(name, responseCreateUser.jsonPath().getString("name"), "name key is not present in the body"), // jsonPath().getString() -> gets value using a key from the JSON Response
//                () -> assertEquals(email, responseCreateUser.jsonPath().getString("email"), "email key is not present in the body"),
//                // here we are checking if the Response from the Create User Request is indeed correct:
//                // (we have this in the event if, for example - the Request is being processed, but not getting created in the Database, we cannot just rely on the Response, we MUST verify the Database)
//                // this issue can appear because the DB simply doesn't have enough character allowance for what we are asking it to create, Backend will not catch this issue!
//                () -> assertEquals(200, responseGetUserById.getStatusCode(), "Status Codes are not the same"),
//                () -> assertEquals(email, responseGetUserById.jsonPath().getString("email"), "email key is not present in the body"),
//                () -> assertEquals(email, responseGetUserById.jsonPath().getString("email"), "email key is not present in the body"),
//                () -> assertTrue(responseGetUserById.getBody().asString().contains("id"), "id key is not present in the body"), // here we are verifying if the "id" keyword is present in the body
//                () -> assertTrue(responseGetUserById.getBody().asString().contains("name"), "name key is not present in the body"),
//                () -> assertTrue(responseGetUserById.getBody().asString().contains("email"), "email key is not present in the body"),
//                () -> assertTrue(responseGetUserById.getBody().asString().contains("status"), "status key is not present in the body"),
//                () -> assertTrue(responseGetUserById.getBody().asString().contains("gender"), "gender key is not present in the body")
//        );
//    }
//
//    // how to test delete user and make it independent
//    // 1) create a new user
//    // 2) get the user, validate that it exists
//    // 3) delete the user
//    // 4) get user, validate the user doesn't exist
//
//    // we can make it as a method then call whenever we want to create or delete
//    // in the Hooks we have Before and After, so that Before Test we can create the user and After Test we va delete the user
//    // we have to always clean up the database after our tests, because our tests might fail later
//
//    @Test
//    public void deleteUserTest() {
//        RestAssured.baseURI = APIHOST + APIV;
//        String name = FAKER.name().fullName();
//        String email = FAKER.internet().emailAddress();
//
//        String body = "{\n" +
//                "    \"name\": \"" + name + "\",\n" +
//                "    \"email\": \"" + email + "\",\n" +
//                "    \"gender\": \"male\",\n" +
//                "    \"status\": \"inactive\"\n" +
//                "}";
//
//        Response responseCreateUser = RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .headers("Authorization", "Bearer " + AUTH)
//                .accept(ContentType.JSON)
//
//                .body(body)
//                .when()
//                .post("/users");
//        // we are using this 3 times, and that is why it is easier to save it into a variable
//        String userId = responseCreateUser.jsonPath().getString("id");
//        // we are assuming that our user has been created, if the user has not been created - the test will vot continue further
//        // (we cannot delete the user that doesn't exist in the system)
//        Assumptions.assumeTrue(responseCreateUser.getStatusCode() == 201, "Create user didn't return 201 status code");
//
//        Response responseGetUserById = RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .headers("Authorization", "Bearer " + AUTH)
//                .accept(ContentType.JSON)
//                .when()
//                .pathParam("userId", userId)
//                .get("/users/{userId}");
//
//        Assumptions.assumeTrue(responseGetUserById.getStatusCode() == 200, "GET user didn't return 200 status code");
//
//        Response responseDeleteUser = RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .headers("Authorization", "Bearer " + AUTH)
//                .accept(ContentType.JSON)
//                .when()
//                .pathParam("userId", userId)
//                // delete method is user here
//                .delete("/users/{userId}");
//
//        Assumptions.assumeTrue(responseDeleteUser.getStatusCode() == 204, "DELETE user did not return 204 Status Code");
//
//        // save GET Response after DELETE into a "responseGetUserByIdAfterDelete" variable
//        Response responseGetUserByIdAfterDelete = RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .headers("Authorization", "Bearer " + AUTH)
//                .accept(ContentType.JSON)
//                .when()
//                .pathParam("userId", userId)
//                .get("/users/{userId}");
//
//        assertAll(
//                // 1) assert the status code of the DELETE Request
//                () -> assertEquals(204, responseDeleteUser.getStatusCode(), "Status Codes are not the same"),
//                () -> assertEquals(404, responseGetUserByIdAfterDelete.getStatusCode(), "Status Codes are not the same"),
//                () -> assertEquals("Resource not found", responseGetUserByIdAfterDelete.jsonPath().getString("message"), "Message key is not present in the response with status code 404")
//        );
//    }
//
//    // what do we need to UPDATE the User:
//    // create
//    // validate it exists
//    // update the user =
//    // get user
//    // assert that the data is saved
//    @Test
//    public void updateUserTest() {
//        RestAssured.baseURI = APIHOST + APIV;
//        String nameUpdated = FAKER.name().fullName();
//        String emailUpdated = FAKER.internet().emailAddress();
//
//        String bodyUpdate = "{\n" +
//                "    \"name\": \"Maryna TEST RESTAssured\",\n" +
//                "    \"email\": \"updatedrandomemail@gmail.com\",\n" +
//                "    \"gender\": \"female\",\n" +
//                "    \"status\": \"inactive\"\n" +
//                "}";
//        // saved put request to the response variable
//        Response responseUpdateUserById = RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + AUTH)
//                .accept(ContentType.JSON)
//                .body(bodyUpdate)
//                .when()
//                .pathParam("userId", "1016407") // "id" value here will be the same as we created using the post method above, we can grab it from the console;
//                .put("/users/{userId}");
//
//        Assumptions.assumeTrue(responseUpdateUserById.getStatusCode() == 200, "Update User did not return 200 Status Code");
//
//        Response responseGetUserByIdAfterDelete = RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .headers("Authorization", "Bearer " + AUTH)
//                .accept(ContentType.JSON)
//                .when()
//                .pathParam("userId", userId)
//                .get("/users/{userId}");
//
//        assertAll(
//                // 1) assert the status code of the DELETE Request
//                () -> assertEquals(200, responseUpdateUserById.getStatusCode(), "Status Codes are not the same"),
//                () -> assertEquals(nameUpdated, responseUpdateUserById.jsonPath().getString("name"), "Names are not the same"),
//                () -> assertEquals(emailUpdated, responseUpdateUserById.jsonPath().getString("email"), "Emails are not the same"),
//                () -> assertEquals("active", responseUpdateUserById.jsonPath().getString("status"), "Statuses are not the same"),
//                () -> assertEquals(200, responseGetUserByIdAfterDelete.getStatusCode(), "Status Codes are not the same"),
//                () -> assertEquals(nameUpdated, responseGetUserByIdAfterDelete.jsonPath().getString("name"), "Names are not the same"),
//                () -> assertEquals(emailUpdated, responseGetUserByIdAfterDelete.jsonPath().getString("email"), "Emails are not the same"),
//                () -> assertEquals("active", responseGetUserByIdAfterDelete.jsonPath().getString("status"), "Statuses are not the same")
//        );
//    }
//}
