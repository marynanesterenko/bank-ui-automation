package APIpractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class REST {

    // usually these private variables will be provided in the config.properties file
    // this is where we will provide the base URl
    private static final String APIHOST = "https://gorest.co.in/public";

    // this is where we will provide the version
    private static final String APIV = "/v2";

    public static void main(String[] args) {

        RestAssured.baseURI = APIHOST + APIV;
        String authorization = "3443366bf7b323f317f245944e21ad1a53d6941839ae35e48b99ebda7062a66b";

        // 1. GET method: LIST users
        // creating a variable to store the Request that we will be sending
        // Request stores the headers, the body, etc.
        // this is our structure for GET Request to get a list all the users:
        Response responseListUsers = RestAssured
                .given()
                .contentType(ContentType.JSON) //header in our Postman Request, this is the body our Request contains
                .header("Authorization", "Bearer " + authorization)
                .accept(ContentType.JSON) // this is another header in our Postman Request, in "accept" we are mentioning, what type of body we want to receive with our Request
                // .then() - we will not be using this in our tests. REST Assured has some Asserts in it's libraries
                // such as .validate(), but we will not be using REST Assured's assertions, we will be using the ones from JUnit
                .when()
                .get("/users");

        System.out.println(responseListUsers.asString());
        System.out.println(responseListUsers.getStatusCode());

        // 2. GET method: get user by it's "id"
        // How to retrieve a specific User from the list? There are two ways:
        // 1. hard code the userId value inside the .get("/users/1016798"); OR
        // 2. we can also call the ".pathParam()" method and pass the key:value to it, as an argument; we also need to make sure we add the "key" to the .get() method
        // !NOTE: (key will be "userId" and the value will be an actual User Object id)
        Response responseGetUserById = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authorization)
                .accept(ContentType.JSON)
                .when()
                .pathParam("userId", "1016798")
                .get("/users/{userId}");

        System.out.println(responseGetUserById.asString());
        System.out.println(responseGetUserById.getStatusCode());

        // 3. CREATE User - POST method:
        // Triple A structure:
        // Arrange
        // Act
        // Assert
        // since we are required to provide the body in the POST Request, let's create the variable where we will store it:
        String body = "{\n" +
                "    \"name\": \"Maryna TEST RESTAssured\",\n" +
                "    \"email\": \"someeamail@devx.com\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"status\": \"active\"\n" +
                "}";

        Response responseCreateUser = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authorization)
                .accept(ContentType.JSON)
                .body(body) // this is specific to POST (we might also be needing this method in PUT and PATCH methods)
                .when()
                .post("/users");

        System.out.println(responseCreateUser.asString());
        System.out.println(responseCreateUser.getStatusCode());

        // 4. UPDATE User - PUT method:
        // since we are required to provide the body in the POST Request, let's create the variable where we will store it:
        String bodyUpdate = "{\n" +
                "    \"name\": \"Maryna TEST RESTAssured\",\n" +
                "    \"email\": \"updatedrandomemail@gmail.com\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"status\": \"inactive\"\n" +
                "}";

        Response responseUpdateUserById = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authorization)
                .accept(ContentType.JSON)
                .body(bodyUpdate)
                .when()
                .pathParam("userId", "1016407") // "id" value here will be the same as we created using the post method above, we can grab it from the console;
                .put("/users/{userId}");

        System.out.println(responseUpdateUserById.asString());
        System.out.println(responseUpdateUserById.getStatusCode());

        // 4. DELETE User - DELETE method:
        Response responseDeleteUserById = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authorization)
                .accept(ContentType.JSON)
                .when()
                .pathParam("userId", "1016407")
                .delete("/users/{userId}");

        System.out.println(responseDeleteUserById.asString());
        System.out.println(responseDeleteUserById.getStatusCode()); // EXPECTED: 204 - when we run for the first time, 404 - if running second time

        // !NOTE: in order to check how long it took us to get the response (payload) after we sent the Request - we can start the timer before the Request and stop the timer after
    }
}
