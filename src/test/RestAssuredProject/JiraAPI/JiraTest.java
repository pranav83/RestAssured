package JiraAPI;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JiraTest {

    @Test
   public void jiraAuto(){

        RestAssured.baseURI = "http://localhost:8080";
        //login
        SessionFilter session = new SessionFilter();
     String response = given().header("Content-Type","application/json")
                .body("{\n" +
                        "    \"username\": \"ppothiwala\",\n" +
                        "    \"password\": \"Neelraj11\"\n" +
                        "}").log().all().filter(session).when().post("/rest/auth/1/session").then().extract().response().asString();



        given().pathParam("key","10004").log().all().header("Content-Type","application/json")
                .body("{\n" +
                        "    \"body\": \"This is my first jira Automation comment.\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}").filter(session).when().post("/rest/api/2/issue/{key}/comment")
                .then().log().all().assertThat().statusCode(201);

   }
}
