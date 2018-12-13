import java.io.IOException;

import CollaboratorClasses.Collaborator;
import Helper.CollaboratorsBuilder;
import Helper.RequestsApacheHTTPClient;
import Helper.RequestsRestAssured;
import org.apache.http.auth.AuthenticationException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task1L6 {

    private static final String USER_LOGIN = "Victorl";
    private static final String USER_TOKEN = "eb21caba2800f479c34f0abee5c0905a3dee9027";
    private static final String REPO_OWNER = "VictorLVA";
    private static final String REPO_NAME = "AutomationCourses";
    private static final String REPO_POSITIVE_INVITATION_USER = "dihnatsyeu";
    private static final String REPO_NEGATIVE_INVITATION_USER = "asulzhyts";
    private static final String GITHUB_API_URI = "https://api.github.com";
    private static final String ENDPOINT_REPO = "/repos/" + REPO_OWNER + "/" + REPO_NAME;
    private static final String ENDPOINT_REPO_COLLABORATORS = ENDPOINT_REPO + "/collaborators";

    @Test
    public void pingGitHubWithRestAssured() {
        Assert.assertEquals(
                RequestsRestAssured.requestGet(GITHUB_API_URI).statusCode(),
                200,
                "RestAssured: GitHub API cannot be accessed -"
        );
    }

    @Test(dependsOnMethods = "pingGitHubWithRestAssured")
    public void repoExistWithRestAssured() {
        Assert.assertEquals(
                RequestsRestAssured.requestGet(GITHUB_API_URI + ENDPOINT_REPO).statusCode(),
                200,
                "RestAssured: Repo cannot be accessed -"
        );
    }

    @Test(dependsOnMethods = "repoExistWithRestAssured")
    public void repoCollaboratorsEndpointWithRestAssured() {
        Assert.assertEquals(
                RequestsRestAssured.requestGet(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS, USER_LOGIN, USER_TOKEN).statusCode(),
                200,
                "RestAssured: Collaborators endpoint cannot be accessed -"
        );
    }

    @Test(dependsOnMethods = "repoCollaboratorsEndpointWithRestAssured")
    public void getCollaboratorsWithRestAssured() throws IOException {
        Collaborator[] repoCollaborators =
                CollaboratorsBuilder.createCollaborators(RequestsRestAssured.requestGet(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS, USER_LOGIN, USER_TOKEN));
        Assert.assertNotNull(
                repoCollaborators,
                "RestAssured: GitHub API isn't return any collaborators"
        );
        int i = 1;
        System.out.println("Collaborators for the \"" + REPO_NAME + "\" repository:");
        for (Collaborator eachCollaborator : repoCollaborators) {
            System.out.println(i + ". " + eachCollaborator.getLogin());
            i++;
        }
    }

    @Test(dependsOnMethods = "repoExistWithRestAssured")
    public void createPositiveInvitationWithRestAssured() {
        Assert.assertEquals(RequestsRestAssured.requestPut(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS +
                                                                   "/" + REPO_POSITIVE_INVITATION_USER, USER_LOGIN, USER_TOKEN).statusCode(),
                            201,
                            "RestAssured: Cannot invite a collaborator -"
        );
    }

    @Test(dependsOnMethods = "repoExistWithRestAssured")
    public void createNegativeInvitationWithRestAssured() {
        Assert.assertEquals(RequestsRestAssured.requestPut(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS +
                                                                   "/" + REPO_NEGATIVE_INVITATION_USER, USER_LOGIN, USER_TOKEN).statusCode(),
                            204,
                            "RestAssured: Collaborator can be invited again -"
        );
    }

    @Test
    public void pingGitHubWithApacheHTTPClient() throws IOException {
        Assert.assertEquals(
                RequestsApacheHTTPClient.requestGet(GITHUB_API_URI).getStatusLine().getStatusCode(),
                200,
                "ApacheHTTPClient: GitHub API cannot be accessed -"
        );
    }

    @Test(dependsOnMethods = "pingGitHubWithApacheHTTPClient")
    public void repoExistWithApacheHTTPClient() throws IOException {
        Assert.assertEquals(
                RequestsApacheHTTPClient.requestGet(GITHUB_API_URI + ENDPOINT_REPO).getStatusLine().getStatusCode(),
                200,
                "ApacheHTTPClient: Repo cannot be accessed -"
        );
    }

    @Test(dependsOnMethods = "repoExistWithApacheHTTPClient")
    public void repoCollaboratorsEndpointWithApacheHTTPClient() throws IOException, AuthenticationException {
        Assert.assertEquals(
                RequestsApacheHTTPClient.requestGet(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS, USER_LOGIN, USER_TOKEN).getStatusLine().getStatusCode(),
                200,
                "ApacheHTTPClient: Collaborators endpoint cannot be accessed -"
        );
    }

    @Test(dependsOnMethods = "repoCollaboratorsEndpointWithApacheHTTPClient")
    public void getCollaboratorsWithApacheHTTPClient() throws IOException, AuthenticationException {
        Collaborator[] repoCollaborators =
                CollaboratorsBuilder.createCollaborators(
                        RequestsApacheHTTPClient.requestGet(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS, USER_LOGIN, USER_TOKEN));
        Assert.assertNotNull(
                repoCollaborators,
                "ApacheHTTPClient: GitHub API isn't return any collaborators"
        );
        int i = 1;
        System.out.println("Collaborators for the \"" + REPO_NAME + "\" repository:");
        for (Collaborator eachCollaborator : repoCollaborators) {
            System.out.println(i + ". " + eachCollaborator.getLogin());
            i++;
        }
    }

    @Test(dependsOnMethods = "repoExistWithApacheHTTPClient")
    public void createPositiveInvitationWithApacheHTTPClient() throws IOException, AuthenticationException {
        Assert.assertEquals(RequestsApacheHTTPClient.requestPut(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS +
                                                                        "/" + REPO_POSITIVE_INVITATION_USER, USER_LOGIN, USER_TOKEN)
                                                    .getStatusLine()
                                                    .getStatusCode(),
                            201,
                            "ApacheHTTPClient: Cannot invite a collaborator -"
        );
    }

    @Test(dependsOnMethods = "repoExistWithApacheHTTPClient")
    public void createNegativeInvitationWithApacheHTTPClient() throws IOException, AuthenticationException {
        Assert.assertEquals(RequestsApacheHTTPClient.requestPut(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS +
                                                                        "/" + REPO_NEGATIVE_INVITATION_USER, USER_LOGIN, USER_TOKEN)
                                                    .getStatusLine()
                                                    .getStatusCode(),
                            204,
                            "ApacheHTTPClient: Collaborator can be invited again -"
        );
    }
}