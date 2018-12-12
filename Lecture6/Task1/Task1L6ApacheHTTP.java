import java.io.IOException;

import CollaboratorClasses.Collaborator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task1L6ApacheHTTP {

    private static final String USER_LOGIN = "Victorl";
    private static final String USER_TOKEN = "a3f629857c861a20af7f4c6bd89bd86c6082bfad";
    private static final String REPO_OWNER = "VictorLVA";
    private static final String REPO_NAME = "AutomationCourses";
    private static final String REPO_POSITIVE_INVITATION_USER = "dihnatsyeu";
    private static final String REPO_NEGATIVE_INVITATION_USER = "asulzhyts";
    private static final String GITHUB_API_URI = "https://api.github.com";
    private static final String ENDPOINT_REPO = "/repos/" + REPO_OWNER + "/" + REPO_NAME;
    private static final String ENDPOINT_REPO_COLLABORATORS = ENDPOINT_REPO + "/collaborators";

    @Test
    public void pingHostTest() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(GITHUB_API_URI);
        HttpResponse response = client.execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200, "GitHub API cannot be accessed -");
    }

    @Test(dependsOnMethods = "pingHostTest")
    public void repExistTest() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(GITHUB_API_URI + ENDPOINT_REPO);
        HttpResponse response = client.execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200, "Needed repo cannot be accessed -");
    }

    @Test(dependsOnMethods = "repExistTest")
    public void getCollaboratorsTest() throws AuthenticationException, IOException {
        UsernamePasswordCredentials gitHubCredentials = new UsernamePasswordCredentials(USER_LOGIN, USER_TOKEN);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS);
        request.addHeader(new BasicScheme().authenticate(gitHubCredentials, request, null));
        HttpResponse fullResponse = client.execute(request);
        Assert.assertEquals(fullResponse.getStatusLine().getStatusCode(), 200, "Cannot receive repo collaborators -");
        String responseBodyJSON = EntityUtils.toString(fullResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        Collaborator[] repoCollaborators = objectMapper.readValue(responseBodyJSON, Collaborator[].class);
        int i = 1;
        System.out.println("Collaborators for the \"" + REPO_NAME + "\" repository:");
        for (Collaborator eachCollaborator : repoCollaborators) {
            System.out.println(i + ". " + eachCollaborator.getLogin());
            i++;
        }
    }

    @Test(dependsOnMethods = "repExistTest")
    public void createPositiveInvitationTest() throws AuthenticationException, IOException {
        UsernamePasswordCredentials gitHubCredentials = new UsernamePasswordCredentials(USER_LOGIN, USER_TOKEN);
        HttpClient client = HttpClientBuilder.create().build();
        HttpPut request = new HttpPut(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS + "/" + REPO_POSITIVE_INVITATION_USER);
        request.addHeader(new BasicScheme().authenticate(gitHubCredentials, request, null));
        HttpResponse fullResponse = client.execute(request);
        Assert.assertEquals(fullResponse.getStatusLine().getStatusCode(), 201, "Cannot invite a collaborator -");
    }

    @Test(dependsOnMethods = "repExistTest")
    public void createNegativeInvitationTest() throws AuthenticationException, IOException {
        UsernamePasswordCredentials gitHubCredentials = new UsernamePasswordCredentials(USER_LOGIN, USER_TOKEN);
        HttpClient client = HttpClientBuilder.create().build();
        HttpPut request = new HttpPut(GITHUB_API_URI + ENDPOINT_REPO_COLLABORATORS + "/" + REPO_NEGATIVE_INVITATION_USER);
        request.addHeader(new BasicScheme().authenticate(gitHubCredentials, request, null));
        HttpResponse fullResponse = client.execute(request);
        Assert.assertEquals(fullResponse.getStatusLine().getStatusCode(), 204, "Collaborator can be invited again -");
    }
}
