package HttpClientsImplementation;

import java.io.IOException;

import Constants.HttpMethods;
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
    private static final String ERROR_MESSAGE = "Something went wrong => ApacheHttpClient)";

    private ApacheHttpClient() {
    }

    public static HttpResponse doRequest(HttpMethods httpMethods, RequestData requestData) {
        switch (httpMethods) {
            case GET: {
                return requestGet(requestData);
            }
            case PUT: {
                return requestPut(requestData);
            }
            default:
                throw new RuntimeException(ERROR_MESSAGE);
        }
    }

    private static HttpResponse requestGet(RequestData requestData) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(requestData.getURI());
            if (isContainAuthData(requestData)) {
                addAuthDataToHeader(requestData, request);
            }
            return client.execute(request);
        } catch (IOException ioEx) {
            System.out.println(ERROR_MESSAGE_IO_EXCEPTION);
        }
        throw new RuntimeException("Something went wrong (ApacheHttpClient => requestGet");
    }

    private static HttpResponse requestPut(RequestData requestData) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPut request = new HttpPut(requestData.getURI());
            if (isContainAuthData(requestData)) {
                addAuthDataToHeader(requestData, request);
            }
            return client.execute(request);
        } catch (IOException ioEx) {
            System.out.println(ERROR_MESSAGE_IO_EXCEPTION);
        }
        throw new RuntimeException("Something went wrong (ApacheHttpClient => requestPut");
    }

    private static boolean isContainAuthData(RequestData requestData) {
        return (requestData.getLogin() != null && requestData.getToken() != null);
    }

    private static void addAuthDataToHeader(RequestData requestData, HttpGet request) {
        UsernamePasswordCredentials gitHubCredentials = new UsernamePasswordCredentials(requestData.getLogin(), requestData.getToken());
        try {
            request.addHeader(new BasicScheme().authenticate(gitHubCredentials, request, null));
        } catch (AuthenticationException authEx) {
            System.out.println(ERROR_MESSAGE_AUTH_EXCEPTION);
        }
    }

    private static void addAuthDataToHeader(RequestData requestData, HttpPut request) {
        UsernamePasswordCredentials gitHubCredentials = new UsernamePasswordCredentials(requestData.getLogin(), requestData.getToken());
        try {
            request.addHeader(new BasicScheme().authenticate(gitHubCredentials, request, null));
        } catch (AuthenticationException authEx) {
            System.out.println(ERROR_MESSAGE_AUTH_EXCEPTION);
        }
    }
}