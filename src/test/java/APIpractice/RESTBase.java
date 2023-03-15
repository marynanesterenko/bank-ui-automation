package APIpractice;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public class RESTBase {
    public final Faker FAKER = new Faker();
    public final String APIHOST = "https://gorest.co.in/public";
    public final String APIV = "/v2";
    public final String AUTH = "4a5df07f01f92a0b18a513fe4176f2e030c9bc4a6e4a18e43daea56172202843";
    RESTClient restClient = new RESTClient();


    @BeforeEach
    public void baseUrlSetup() {
        // Setting up baseURL by adding host to version of api
        RestAssured.baseURI = APIHOST + APIV;
    }
}
