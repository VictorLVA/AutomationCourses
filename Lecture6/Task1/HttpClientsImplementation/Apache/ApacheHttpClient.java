package HttpClientsImplementation.Apache;

import java.io.IOException;

import Constants.HttpMethods;
import HttpClientsImplementation.CustomHttpClient;
import HttpClientsImplementation.Utils.CustomResponse;
import HttpClientsImplementation.Utils.RequestData;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class ApacheHttpClient implements CustomHttpClient {

    public ApacheHttpClient() {
    }

    @Override
    public CustomResponse doRequest(HttpMethods httpMethods, RequestData requestData) {
        ApacheHttp apacheHttp = new ApacheHttp();
        HttpResponse apacheResponse = apacheHttp.doRequest(httpMethods, requestData);
        String responseBody = "0";
        try {
            if (apacheResponse.getEntity() != null) {
                responseBody = EntityUtils.toString(apacheResponse.getEntity());
            }
        } catch (IOException ioEx) {
            System.out.println("Something went wrong => ApacheHttpClient class, method 'doRequest'");
        }
        int statusCode = apacheResponse.getStatusLine().getStatusCode();
        return new CustomResponse(responseBody, statusCode);
    }
}