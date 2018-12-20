package HttpClientsImplementation.Utils;

import Constants.HttpClients;
import Constants.HttpMethods;
import HttpClientsImplementation.ApacheHttpClient;
import HttpClientsImplementation.RestAssured;

public class RequestExecutor {

    private static final String ERROR_MESSAGE = "Something went wrong => RequestExecutor)";

    public RequestExecutor() {
    }

    public CustomResponse executeAndGetResponse(HttpClients httpClients, HttpMethods httpMethods, RequestData requestData) {
        switch (httpClients) {
            case RestAssured:
                return new CustomResponse(RestAssured.doRequest(httpMethods, requestData));
            case ApacheHttpClient: {
                return new CustomResponse(ApacheHttpClient.doRequest(httpMethods, requestData));
            }
            default:
                throw new RuntimeException(ERROR_MESSAGE);
        }
    }
}