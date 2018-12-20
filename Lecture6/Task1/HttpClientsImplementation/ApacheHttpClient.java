package HttpClientsImplementation;

import java.io.IOException;

import HttpClientsImplementation.Utils.RequestData;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.HttpClientBuilder;

public class ApacheHttpClient {

    private static final String ERROR_MESSAGE_IO_EXCEPTION = "Something went wrong (ApacheHttpClient => IOException)";
    private static final String ERROR_MESSAGE_AUTH_EXCEPTION = "Something went wrong (ApacheHttpClient => AuthenticationException)";

    private ApacheHttpClient() {
    }

    public static HttpResponse requestGet(RequestData requestData) {
        try {
            if (requestData.getLogin() == null && requestData.getToken() == null) {
                HttpClient client = HttpClientBuilder.create().build();
                HttpGet request = new HttpGet(requestData.getURI());
                return client.execute(request);
            } else {
                UsernamePasswordCredentials gitHubCredentials = new UsernamePasswordCredentials(requestData.getLogin(), requestData.getToken());
                HttpClient client = HttpClientBuilder.create().build();
                HttpGet request = new HttpGet(requestData.getURI());
                request.addHeader(new BasicScheme().authenticate(gitHubCredentials, request, null));
                return client.execute(request);
            }
        } catch (IOException ioEx) {
            System.out.println(ERROR_MESSAGE_IO_EXCEPTION);
        } catch (AuthenticationException authEx) {
            System.out.println(ERROR_MESSAGE_AUTH_EXCEPTION);
        }
        throw new RuntimeException("Something went wrong (ApacheHttpClient => requestGet");
    }

    public static HttpResponse requestPut(RequestData requestData) {
        try {
            UsernamePasswordCredentials gitHubCredentials = new UsernamePasswordCredentials(requestData.getLogin(), requestData.getToken());
            HttpClient client = HttpClientBuilder.create().build();
            HttpPut request = new HttpPut(requestData.getURI());
            request.addHeader(new BasicScheme().authenticate(gitHubCredentials, request, null));
            return client.execute(request);
        } catch (IOException ioEx) {
            System.out.println(ERROR_MESSAGE_IO_EXCEPTION);
        } catch (AuthenticationException authEx) {
            System.out.println(ERROR_MESSAGE_AUTH_EXCEPTION);
        }
        throw new RuntimeException("Something went wrong (ApacheHttpClient => requestPut");
    }
}