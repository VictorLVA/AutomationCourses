import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;

// TODO: 11.12.2018 keytool -import -alias example -keystore  C:\Program Files (x86)\Java\jre1.6.0_22\lib\security\cacerts -file example.cer

public class Task1L6 {

    private static final String USER_LOGIN = "Victorl";
    private static final String USER_TOKEN = "";
    private static final String REPO_OWNER = "VictorLVA";
    private static final String REPO_NAME = "AutomationCourses";
    private static final String ENDPOINT_REPO_COLLABORATORS = "/repos/" + REPO_OWNER + "/" + REPO_NAME + "/collaborators";

    @BeforeTest
    public void initHost() {
        RestAssured.baseURI = "https://api.github.com";
    }

    @Test
    public void pingTest() {
        given().auth().preemptive().basic(USER_LOGIN, USER_TOKEN)
               .when().get(baseURI)
               .then().statusCode(200);
    }

    @Test(dependsOnMethods = "pingTest")
    public void getColaboratorsTest() {
        Response response = given().auth().preemptive().basic(USER_LOGIN, USER_TOKEN)
                                   .get(ENDPOINT_REPO_COLLABORATORS);
        System.out.println(response.asString());
    }
}
