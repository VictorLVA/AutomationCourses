import CollaboratorClasses.Collaborator;
import CollaboratorClasses.Utils.CollaboratorsBuilder;
import Constants.HttpMethods;
import HttpClientsImplementation.Apache.ApacheHttpClient;
import HttpClientsImplementation.CustomHttpClient;
import HttpClientsImplementation.RestAssured.RestAssuredClient;
import HttpClientsImplementation.Utils.CustomResponse;
import HttpClientsImplementation.Utils.RequestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task1L6 {

    private static final String USER_LOGIN = "Victorl";
    private static final String USER_TOKEN = "2049ca840f4050ec042cae4c2f8c3a1f5f730134";
    private static final String REPO_OWNER = "VictorLVA";
    private static final String REPO_NAME = "AutomationCourses";
    private static final String REPO_POSITIVE_INVITATION_USER = "dihnatsyeu";
    private static final String REPO_NEGATIVE_INVITATION_USER = "asulzhyts";
    private static final String GITHUB_API_URI = "https://api.github.com";
    private static final String ENDPOINT_REPO = "/repos/" + REPO_OWNER + "/" + REPO_NAME;
    private static final String ENDPOINT_REPO_COLLABORATORS = ENDPOINT_REPO + "/collaborators";

    private static CustomHttpClient restAssuredClient = new RestAssuredClient();
    private static CustomHttpClient apacheHttpClient = new ApacheHttpClient();

    @Test
    public void pingGitHubWithRestAssured() {
        RequestData requestToGitHubData = new RequestData(GITHUB_API_URI, USER_LOGIN, USER_TOKEN);
        CustomResponse gitHubResponse = restAssuredClient.doRequest(HttpMethods.GET, requestToGitHubData);
        Assert.assertEquals(
                gitHubResponse.getResponseStatusCode(),
                200,
                "RestAssured: GitHub API cannot be accessed -"
        );
    }

    @Test(dependsOnMethods = "pingGitHubWithRestAssured")
    public void repoExistWithRestAssured() {
        RequestData requestToRepoData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO, USER_LOGIN, USER_TOKEN);
        CustomResponse repoResponse = restAssuredClient.doRequest(HttpMethods.GET, requestToRepoData);
        Assert.assertEquals(
                repoResponse.getResponseStatusCode(),
                200,
                "RestAssured: Repo cannot be accessed -"
        );
    }

    @Test(dependsOnMethods = "repoExistWithRestAssured")
    public void repoCollaboratorsEndpointWithRestAssured() {
        RequestData requestToCollaboratorsData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS, USER_LOGIN, USER_TOKEN);
        CustomResponse collaboratorsResponse = restAssuredClient.doRequest(HttpMethods.GET, requestToCollaboratorsData);
        Assert.assertEquals(
                collaboratorsResponse.getResponseStatusCode(),
                200,
                "RestAssured: Collaborators endpoint cannot be accessed -"
        );
    }

    @Test(dependsOnMethods = "repoCollaboratorsEndpointWithRestAssured")
    public void getCollaboratorsWithRestAssured() {
        RequestData requestToCollaboratorsData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS, USER_LOGIN, USER_TOKEN);
        CustomResponse collaboratorsResponse = restAssuredClient.doRequest(HttpMethods.GET, requestToCollaboratorsData);
        Collaborator[] repoCollaborators = CollaboratorsBuilder.createCollaborators(collaboratorsResponse);
        Assert.assertNotNull(
                repoCollaborators,
                "RestAssured: GitHub API isn't return any collaborators"
        );
        int i = 1;
        System.out.println("RestAssured. Collaborators for the \"" + REPO_NAME + "\" repository:");
        for (Collaborator eachCollaborator : repoCollaborators) {
            System.out.println(i + ". " + eachCollaborator.getLogin());
            i++;
        }
    }

    @Test(dependsOnMethods = "repoExistWithRestAssured")
    public void createPositiveInvitationWithRestAssured() {
        RequestData requestToRepoInvitationData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS +
                                                                          "/" + REPO_POSITIVE_INVITATION_USER, USER_LOGIN, USER_TOKEN);
        CustomResponse collaboratorsInvitationResponse = restAssuredClient.doRequest(HttpMethods.PUT, requestToRepoInvitationData);
        Assert.assertEquals(
                collaboratorsInvitationResponse.getResponseStatusCode(),
                201,
                "RestAssured: Cannot invite a collaborator -"
        );
    }

    @Test(dependsOnMethods = "repoExistWithRestAssured")
    public void createNegativeInvitationWithRestAssured() {
        RequestData requestToRepoInvitationData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS +
                                                                          "/" + REPO_NEGATIVE_INVITATION_USER, USER_LOGIN, USER_TOKEN);
        CustomResponse collaboratorsInvitationResponse = restAssuredClient.doRequest(HttpMethods.PUT, requestToRepoInvitationData);
        Assert.assertEquals(
                collaboratorsInvitationResponse.getResponseStatusCode(),
                204,
                "RestAssured: Collaborator can be invited again -"
        );
    }

    @Test
    public void pingGitHubWithApacheHTTPClient() {
        RequestData requestToGitHubData = new RequestData(GITHUB_API_URI, USER_LOGIN, USER_TOKEN);
        CustomResponse gitHubResponse = apacheHttpClient.doRequest(HttpMethods.GET, requestToGitHubData);
        Assert.assertEquals(
                gitHubResponse.getResponseStatusCode(),
                200,
                "ApacheHTTPClient: GitHub API cannot be accessed -"
        );
    }

    @Test(dependsOnMethods = "pingGitHubWithApacheHTTPClient")
    public void repoExistWithApacheHTTPClient() {
        RequestData requestToRepoData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO, USER_LOGIN, USER_TOKEN);
        CustomResponse repoResponse = apacheHttpClient.doRequest(HttpMethods.GET, requestToRepoData);
        Assert.assertEquals(
                repoResponse.getResponseStatusCode(),
                200,
                "ApacheHTTPClient: Repo cannot be accessed -"
        );
    }

    @Test(dependsOnMethods = "repoExistWithApacheHTTPClient")
    public void repoCollaboratorsEndpointWithApacheHTTPClient() {
        RequestData requestToCollaboratorsData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS, USER_LOGIN, USER_TOKEN);
        CustomResponse collaboratorsResponse = apacheHttpClient.doRequest(HttpMethods.GET, requestToCollaboratorsData);
        Assert.assertEquals(
                collaboratorsResponse.getResponseStatusCode(),
                200,
                "ApacheHTTPClient: Collaborators endpoint cannot be accessed -"
        );
    }

    @Test(dependsOnMethods = "repoCollaboratorsEndpointWithApacheHTTPClient")
    public void getCollaboratorsWithApacheHTTPClient() {
        RequestData requestToCollaboratorsData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS, USER_LOGIN, USER_TOKEN);
        CustomResponse collaboratorsResponse = apacheHttpClient.doRequest(HttpMethods.GET, requestToCollaboratorsData);
        Collaborator[] repoCollaborators = CollaboratorsBuilder.createCollaborators(collaboratorsResponse);
        Assert.assertNotNull(
                repoCollaborators,
                "ApacheHTTPClient: GitHub API isn't return any collaborators"
        );
        int i = 1;
        System.out.println("ApacheHTTPClient. Collaborators for the \"" + REPO_NAME + "\" repository:");
        for (Collaborator eachCollaborator : repoCollaborators) {
            System.out.println(i + ". " + eachCollaborator.getLogin());
            i++;
        }
    }

    @Test(dependsOnMethods = "repoExistWithApacheHTTPClient")
    public void createPositiveInvitationWithApacheHTTPClient() {
        RequestData requestToRepoInvitationData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS +
                                                                          "/" + REPO_POSITIVE_INVITATION_USER, USER_LOGIN, USER_TOKEN);
        CustomResponse collaboratorsInvitationResponse = apacheHttpClient.doRequest(HttpMethods.PUT, requestToRepoInvitationData);
        Assert.assertEquals(
                collaboratorsInvitationResponse.getResponseStatusCode(),
                201,
                "ApacheHTTPClient: Cannot invite a collaborator -"
        );
    }

    @Test(dependsOnMethods = "repoExistWithApacheHTTPClient")
    public void createNegativeInvitationWithApacheHTTPClient() {
        RequestData requestToRepoInvitationData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS +
                                                                          "/" + REPO_NEGATIVE_INVITATION_USER, USER_LOGIN, USER_TOKEN);
        CustomResponse collaboratorsInvitationResponse = apacheHttpClient.doRequest(HttpMethods.PUT, requestToRepoInvitationData);
        Assert.assertEquals(
                collaboratorsInvitationResponse.getResponseStatusCode(),
                204,
                "ApacheHTTPClient: Collaborator can be invited again -"
        );
    }
}