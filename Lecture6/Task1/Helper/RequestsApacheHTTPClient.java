package Helper;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.HttpClientBuilder;

public class RequestsApacheHTTPClient {

    private RequestsApacheHTTPClient() {
    }

    public static HttpResponse requestGet(String URI) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(URI);
        return client.execute(request);
    }

    public static HttpResponse requestGet(String URI, String login, String token) throws IOException, AuthenticationException {
        UsernamePasswordCredentials gitHubCredentials = new UsernamePasswordCredentials(login, token);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(URI);
        request.addHeader(new BasicScheme().authenticate(gitHubCredentials, request, null));
        return client.execute(request);
    }

    public static HttpResponse requestPut(String URI, String login, String token) throws IOException, AuthenticationException {
        UsernamePasswordCredentials gitHubCredentials = new UsernamePasswordCredentials(login, token);
        HttpClient client = HttpClientBuilder.create().build();
        HttpPut request = new HttpPut(URI);
        request.addHeader(new BasicScheme().authenticate(gitHubCredentials, request, null));
        return client.execute(request);
    }
}