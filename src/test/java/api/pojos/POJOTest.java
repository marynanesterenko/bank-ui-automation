package api.pojos;

import api.pojos.UserPOJO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class POJOTest {
    private static final String APIHOST = "https://gorest.co.in/public";
    private static final String APIV = "/v2";
    public static void main(String[] args) {
        RestAssured.baseURI = APIHOST + APIV;
        String authorization = "3443366bf7b323f317f245944e21ad1a53d6941839ae35e48b99ebda7062a66b";
//
//        Response responseListUsers = RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + authorization)
//                .accept(ContentType.JSON)
//                .when()
//                .get("/users");
//        System.out.println(responseListUsers.asString());
//        System.out.println(responseListUsers.getStatusCode());
//
//        Response responseGetUserById = RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + authorization)
//                .accept(ContentType.JSON)
//                .when()
//                .pathParam("userId", "1016798")
//                .get("/users/{userId}");
//        System.out.println(responseGetUserById.asString());
//        System.out.println(responseGetUserById.getStatusCode());

        UserPOJO serializedUser = new UserPOJO("GSON TEST SERIALIZE", "serialize48596@gmail.com", "female", "active");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        gson.toJson(serializedUser);

        Response responseCreateUser = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authorization)
                .accept(ContentType.JSON)
                .body(gson.toJson(serializedUser))
                .when()
                .post("/users");

        System.out.println(responseCreateUser.asString());
        System.out.println(responseCreateUser.getStatusCode());

        // backwards operation, we are converting Json back
        // UsersPojo.class will open the class, and it will look at our variables there
        UserPOJO deserializedUser = gson.fromJson(responseCreateUser.asString(), UserPOJO.class);

//
//        String bodyUpdate = "{\n" +
//                "    \"name\": \"Maryna TEST RESTAssured\",\n" +
//                "    \"email\": \"updatedrandomemail@gmail.com\",\n" +
//                "    \"gender\": \"female\",\n" +
//                "    \"status\": \"inactive\"\n" +
//                "}";
//
//        Response responseUpdateUserById = RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + authorization)
//                .accept(ContentType.JSON)
//                .body(bodyUpdate)
//                .when()
//                .pathParam("userId", "1016407")
//                .put("/users/{userId}");
//
//        System.out.println(responseUpdateUserById.asString());
//        System.out.println(responseUpdateUserById.getStatusCode());
//
//        Response responseDeleteUserById = RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + authorization)
//                .accept(ContentType.JSON)
//                .when()
//                .pathParam("userId", "1016407")
//                .delete("/users/{userId}");
//
//        System.out.println(responseDeleteUserById.asString());
//        System.out.println(responseDeleteUserById.getStatusCode());
    }
}
