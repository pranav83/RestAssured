package RestAssured;

import io.restassured.path.json.JsonPath;

public class ReUsableMethod {

    public static JsonPath rawTojson(String reponse){
        JsonPath js1 = new JsonPath(reponse);
        return js1;
    }
}
