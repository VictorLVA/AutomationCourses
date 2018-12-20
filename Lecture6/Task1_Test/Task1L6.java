import CollaboratorClasses.Collaborator;
import CollaboratorClasses.Utils.CollaboratorsBuilder;
import Constants.HttpClients;
import Constants.HttpMethods;
import HttpClientsImplementation.Utils.RequestData;
import HttpClientsImplementation.Utils.RequestExecutor;
import com.jayway.restassured.response.Response;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task1L6 {

    private static final String USER_LOGIN = "Victorl";
    private static final String USER_TOKEN = "76a4e209f0d582b4c4d904d0c8dc7d57a7279f57";
    private static final String REPO_OWNER = "VictorLVA";
    private static final String REPO_NAME = "AutomationCourses";
    private static final String REPO_POSITIVE_INVITATION_USER = "dihnatsyeu";
    private static final String REPO_NEGATIVE_INVITATION_USER = "asulzhyts";
    private static final String GITHUB_API_URI = "https://api.github.com";
    private static final String ENDPOINT_REPO = "/repos/" + REPO_OWNER + "/" + REPO_NAME;
    private static final String ENDPOINT_REPO_COLLABORATORS = ENDPOINT_REPO + "/collaborators";

    private static RequestExecutor requestExecutor = new RequestExecutor();

    @Test
    public void pingGitHubWithRestAssured() {
        RequestData requestToGitHubData = new RequestData(GITHUB_API_URI);
        Response gitHubResponse = requestExecutor.executeAndGetResponse(HttpClients.RestAssured, HttpMethods.GET, requestToGitHubData);
        Assert.assertEquals(
                gitHubResponse.statusCode(),
                200,
                "RestAssured: GitHub API cannot be accessed -"
        );
    }

    @Test(dependsOnMethods = "pingGitHubWithRestAssured")
    public void repoExistWithRestAssured() {
        RequestData requestToRepoData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO);
        Response repoResponse = requestExecutor.executeAndGetResponse(HttpClients.RestAssured, HttpMethods.GET, requestToRepoData);
        Assert.assertEquals(
                repoResponse.statusCode(),
                200,
                "RestAssured: Repo cannot be accessed -"
        );
    }

    @Test(dependsOnMethods = "repoExistWithRestAssured")
    public void repoCollaboratorsEndpointWithRestAssured() {
        RequestData requestToCollaboratorsData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS, USER_LOGIN, USER_TOKEN);
        Response collaboratorsResponse = requestExecutor.executeAndGetResponse(HttpClients.RestAssured, HttpMethods.GET, requestToCollaboratorsData);
        Assert.assertEquals(
                collaboratorsResponse.statusCode(),
                200,
                "RestAssured: Collaborators endpoint cannot be accessed -"
        );
    }

    @Test(dependsOnMethods = "repoCollaboratorsEndpointWithRestAssured")
    public void getCollaboratorsWithRestAssured() {
        RequestData requestToCollaboratorsData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS, USER_LOGIN, USER_TOKEN);
        Response collaboratorsResponse = requestExecutor.executeAndGetResponse(HttpClients.RestAssured, HttpMethods.GET, requestToCollaboratorsData);
        Collaborator[] repoCollaborators = CollaboratorsBuilder.createCollaborators(collaboratorsResponse);
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
        RequestData requestToRepoInvitationData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS +
                                                                          "/" + REPO_POSITIVE_INVITATION_USER, USER_LOGIN, USER_TOKEN);
        Response collaboratorsInvitationResponse = requestExecutor.executeAndGetResponse(HttpClients.RestAssured, HttpMethods.PUT, requestToRepoInvitationData);
        Assert.assertEquals(
                collaboratorsInvitationResponse.statusCode(),
                201,
                "RestAssured: Cannot invite a collaborator -"
        );
    }

    @Test(dependsOnMethods = "repoExistWithRestAssured")
    public void createNegativeInvitationWithRestAssured() {
        RequestData requestToRepoInvitationData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS +
                                                                          "/" + REPO_NEGATIVE_INVITATION_USER, USER_LOGIN, USER_TOKEN);
        Response collaboratorsInvitationResponse = requestExecutor.executeAndGetResponse(HttpClients.RestAssured, HttpMethods.PUT, requestToRepoInvitationData);
        Assert.assertEquals(
                collaboratorsInvitationResponse.statusCode(),
                204,
                "RestAssured: Collaborator can be invited again -"
        );
    }

    @Test
    public void pingGitHubWithApacheHTTPClient() {
        RequestData requestToGitHubData = new RequestData(GITHUB_API_URI);
        HttpResponse gitHubResponse = requestExecutor.executeAndGetResponse(HttpClients.ApacheHttpClient, HttpMethods.GET, requestToGitHubData);
        Assert.assertEquals(
                gitHubResponse.getStatusLine().getStatusCode(),
                200,
                "ApacheHTTPClient: GitHub API cannot be accessed -"
        );
    }

    @Test(dependsOnMethods = "pingGitHubWithApacheHTTPClient")
    public void repoExistWithApacheHTTPClient() {
        RequestData requestToRepoData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO);
        HttpResponse repoResponse = requestExecutor.executeAndGetResponse(HttpClients.ApacheHttpClient, HttpMethods.GET, requestToRepoData);
        Assert.assertEquals(
                repoResponse.getStatusLine().getStatusCode(),
                200,
                "ApacheHTTPClient: Repo cannot be accessed -"
        );
    }

    @Test(dependsOnMethods = "repoExistWithApacheHTTPClient")
    public void repoCollaboratorsEndpointWithApacheHTTPClient() {
        RequestData requestToCollaboratorsData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS, USER_LOGIN, USER_TOKEN);
        HttpResponse collaboratorsResponse = requestExecutor.executeAndGetResponse(HttpClients.ApacheHttpClient, HttpMethods.GET, requestToCollaboratorsData);
        Assert.assertEquals(
                collaboratorsResponse.getStatusLine().getStatusCode(),
                200,
                "ApacheHTTPClient: Collaborators endpoint cannot be accessed -"
        );
    }

    @Test(dependsOnMethods = "repoCollaboratorsEndpointWithApacheHTTPClient")
    public void getCollaboratorsWithApacheHTTPClient() {
        RequestData requestToCollaboratorsData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS, USER_LOGIN, USER_TOKEN);
        HttpResponse collaboratorsResponse = requestExecutor.executeAndGetResponse(HttpClients.ApacheHttpClient, HttpMethods.GET, requestToCollaboratorsData);
        Collaborator[] repoCollaborators = CollaboratorsBuilder.createCollaborators(collaboratorsResponse);
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
    public void createPositiveInvitationWithApacheHTTPClient() {
        RequestData requestToRepoInvitationData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS +
                                                                          "/" + REPO_POSITIVE_INVITATION_USER, USER_LOGIN, USER_TOKEN);
        HttpResponse collaboratorsInvitationResponse = requestExecutor.executeAndGetResponse(HttpClients.ApacheHttpClient, HttpMethods.PUT,
                                                                                             requestToRepoInvitationData);
        Assert.assertEquals(
                collaboratorsInvitationResponse.getStatusLine().getStatusCode(),
                201,
                "ApacheHTTPClient: Cannot invite a collaborator -"
        );
    }

    @Test(dependsOnMethods = "repoExistWithApacheHTTPClient")
    public void createNegativeInvitationWithApacheHTTPClient() {
        RequestData requestToRepoInvitationData = new RequestData(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS +
                                                                          "/" + REPO_NEGATIVE_INVITATION_USER, USER_LOGIN, USER_TOKEN);
        HttpResponse collaboratorsInvitationResponse = requestExecutor.executeAndGetResponse(HttpClients.ApacheHttpClient, HttpMethods.PUT,
                                                                                             requestToRepoInvitationData);
        Assert.assertEquals(
                collaboratorsInvitationResponse.getStatusLine().getStatusCode(),
                204,
                "ApacheHTTPClient: Collaborator can be invited again -"
        );
    }
}