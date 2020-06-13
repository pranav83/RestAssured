import File.Paylod;
import io.restassured.RestAssured;
import org.junit.Test;
import static org.hamcrest.Matcher.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ValidateAPI {
    /* Validate the add place api
    given() = Input all input details like queryparam header body
    when() = resources
    Then = verify status code response body header server
     */
    Paylod paylod = new Paylod();
    @Test
    public void addAPI(){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given().queryParam("key","qaclick123").header("Content-Type","application/json")
                // here we have created payload class add body there and by creating class we are calling
                //body(payload.addPlace) refer payload class under file package
                .body(paylod.addPlace()).when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope",equalTo("APP")).
                header("Server","Apache/2.4.18 (Ubuntu)")

// Now let update our place and use get method to see updated place present in body






                .log().all();
    }
}
