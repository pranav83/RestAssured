package RestAssured;

import File.Paylod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ValidateResponse {

        /* Now let store complete test in response String and then validate
        use extract string here
         */
        Paylod paylod = new Paylod();
        @Test
        public void addAPI(){
            RestAssured.baseURI = "https://rahulshettyacademy.com";
            // Store complete test in string response
            String response = given().queryParam("key","qaclick123").header("Content-Type","application/json")
                    // here we have created payload class add body there and by creating class we are calling
                    //body(payload.addPlace) refer payload class under file package
                    .body(paylod.addPlace()).when().post("/maps/api/place/add/json")
                    .then().assertThat().statusCode(200).body("scope",equalTo("APP")).
                    header("Server","Apache/2.4.18 (Ubuntu)").extract().asString();
            System.out.println(response);
            // Now let invoke json class to pass json file to validate
            // We will pass response string
            JsonPath js = new JsonPath(response);
            String placeID = js.getString("place_id");
            System.out.println(placeID);
            // Lets update place id you have to given string placeID whic we extract
            String newAddress = "70 Summer walk, USA";
               given().queryParam("key","qaclick123").header("Content-Type","application/json")
                    .body("{\n" +
                            "\"place_id\":\""+placeID+"\",\n" +
                            "\"address\":\""+newAddress+"\",\n" +
                            "\"key\":\"qaclick123\"\n" +
                            "}\n").
                    when().put("/maps/api/place/update/json").then()
                    //Now make sure you use assert here to verify status msg
                    .assertThat().statusCode(200).body("msg",equalTo("Address successfully updated")).log().all();
                    // Now let use get method to validated place is successfully updated
            // We dont need to give header for get method
            String getPlace = given().queryParam("key","qaclick123").queryParam("place_id",placeID).
                    when().get("/maps/api/place/get/json").then() .assertThat().statusCode(200)
            .extract().response().asString();
            ;
            // let again use json path
            JsonPath js1 = new JsonPath(getPlace);
           String actualAdd =  js1.getString("address");
            System.out.println(actualAdd);
            Assert.assertEquals(actualAdd,newAddress);

        }
    }


