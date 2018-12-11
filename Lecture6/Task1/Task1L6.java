import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;

// TODO: 11.12.2018 keytool -import -alias example -keystore  C:\Program Files (x86)\Java\jre1.6.0_22\lib\security\cacerts -file example.cer
// TODO: 11.12.2018 f2cf87799fa7467d02a5f4a95da855536f913dee
public class Task1L6 {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://api.github.com";
        RestAssured.basePath = "/repos/VictorLVA/AutomationCourses/collaborators";
    }

    @Test
    public void basicPingTest() {
        given()
                .auth()
                .preemptive()
                .basic ("Victorl", "f2cf87799fa7467d02a5f4a95da855536f913dee")
                .when()
                .get(baseURI + basePath)
                .then()
                .statusCode(200);
    }

    @Test
    public void mainFlowTest() {
        Response response = given()
                .auth()
                .preemptive()
                .basic ("Victorl", "f2cf87799fa7467d02a5f4a95da855536f913dee")
                .get(baseURI + basePath);
        System.out.println(response.asString());
    }

}
