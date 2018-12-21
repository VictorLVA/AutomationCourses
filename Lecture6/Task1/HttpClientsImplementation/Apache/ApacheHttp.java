package HttpClientsImplementation.Apache;

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

class ApacheHttp {

    ApacheHttp() {
    }

    HttpResponse doRequest(HttpMethods httpMethods, RequestData requestData) {
        switch (httpMethods) {
            case GET: {
                return requestGet(requestData);
            }
            case PUT: {
                return requestPut(requestData);
            }
            default:
                throw new RuntimeException("Something went wrong => ApacheHttp class, method 'doRequest'");
        }
    }

    private HttpResponse requestGet(RequestData requestData) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(requestData.getURI());
            if (isContainAuthData(requestData)) {
                addAuthDataToHeader(requestData, request);
            }
            return client.execute(request);
        } catch (IOException ioEx) {
            System.out.println("Something went wrong => ApacheHttp class, method 'requestGet'");
        }
        throw new RuntimeException("Something went wrong => ApacheHttp class, method 'requestGet'");
    }

    private HttpResponse requestPut(RequestData requestData) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPut request = new HttpPut(requestData.getURI());
            if (isContainAuthData(requestData)) {
                addAuthDataToHeader(requestData, request);
            }
            return client.execute(request);
        } catch (IOException ioEx) {
            System.out.println("Something went wrong => ApacheHttp class, method 'requestPut'");
        }
        throw new RuntimeException("Something went wrong => ApacheHttp class, method 'requestPut'");
    }

    private boolean isContainAuthData(RequestData requestData) {
        return (requestData.getLogin() != null && requestData.getToken() != null);
    }

    private void addAuthDataToHeader(RequestData requestData, HttpGet request) {
        UsernamePasswordCredentials gitHubCredentials = new UsernamePasswordCredentials(requestData.getLogin(), requestData.getToken());
        try {
            request.addHeader(new BasicScheme().authenticate(gitHubCredentials, request, null));
        } catch (AuthenticationException authEx) {
            System.out.println("Something went wrong => ApacheHttp class, method 'addAuthDataToHeader'");
        }
    }

    private void addAuthDataToHeader(RequestData requestData, HttpPut request) {
        UsernamePasswordCredentials gitHubCredentials = new UsernamePasswordCredentials(requestData.getLogin(), requestData.getToken());
        try {
            request.addHeader(new BasicScheme().authenticate(gitHubCredentials, request, null));
        } catch (AuthenticationException authEx) {
            System.out.println("Something went wrong => ApacheHttp class, method 'addAuthDataToHeader'");
        }
    }
}