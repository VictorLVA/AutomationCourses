import java.io.IOException;

import CollaboratorClasses.Collaborator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;

public class Task1L6RestAssured {

    private static final String USER_LOGIN = "Victorl";
    private static final String USER_TOKEN = "a3f629857c861a20af7f4c6bd89bd86c6082bfad";
    private static final String REPO_OWNER = "VictorLVA";
    private static final String REPO_NAME = "AutomationCourses";
    private static final String REPO_POSITIVE_INVITATION_USER = "dihnatsyeu";
    private static final String REPO_NEGATIVE_INVITATION_USER = "asulzhyts";
    private static final String ENDPOINT_REPO = "/repos/" + REPO_OWNER + "/" + REPO_NAME;
    private static final String ENDPOINT_REPO_COLLABORATORS = ENDPOINT_REPO + "/collaborators";

    @BeforeTest
    public void initHost() {
        RestAssured.baseURI = "https://api.github.com";
    }

    @Test
    public void pingHostTest() {
        given()
                .when().get(baseURI)
                .then().statusCode(200);
    }

    @Test(dependsOnMethods = "pingHostTest")
    public void repExistTest() {
        given()
                .when().get(ENDPOINT_REPO)
                .then().statusCode(200);
    }

    @Test(dependsOnMethods = "repExistTest")
    public void getCollaboratorsTest() throws IOException {
        Response fullResponse = given().auth().preemptive().basic(USER_LOGIN, USER_TOKEN)
                                       .when().get(ENDPOINT_REPO_COLLABORATORS);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        Collaborator[] repoCollaborators = objectMapper.readValue(fullResponse.asString(), Collaborator[].class);
        int i = 1;
        System.out.println("Collaborators for the \"" + REPO_NAME + "\" repository:");
        for (Collaborator eachCollaborator : repoCollaborators) {
            System.out.println(i + ". " + eachCollaborator.getLogin());
            i++;
        }
    }

    @Test(dependsOnMethods = "repExistTest")
    public void createPositiveInvitationTest() {
        given().auth().preemptive().basic(USER_LOGIN, USER_TOKEN)
               .when().put(ENDPOINT_REPO_COLLABORATORS + "/" + REPO_POSITIVE_INVITATION_USER)
               .then().statusCode(201);
    }

    @Test(dependsOnMethods = "repExistTest")
    public void createNegativeInvitationTest() {
        given().auth().preemptive().basic(USER_LOGIN, USER_TOKEN)
               .when().put(ENDPOINT_REPO_COLLABORATORS + "/" + REPO_NEGATIVE_INVITATION_USER)
               .then().statusCode(204);
    }
}
