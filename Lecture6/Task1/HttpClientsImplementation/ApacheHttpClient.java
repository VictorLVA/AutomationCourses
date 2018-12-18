package HttpClientsImplementation;

import java.io.IOException;

import HttpClientsImplementation.Utils.RequestDataPreparatory;
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

    public static HttpResponse requestGet(RequestDataPreparatory requestData) {
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
            System.exit(0);
        }
        return null;
    }

    public static HttpResponse requestPut(RequestDataPreparatory requestData) {
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
            System.exit(0);
        }
        return null;
    }
}