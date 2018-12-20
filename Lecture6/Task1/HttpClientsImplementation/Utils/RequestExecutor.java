package HttpClientsImplementation.Utils;

import Constants.HttpClients;
import Constants.HttpMethods;
import HttpClientsImplementation.ApacheHttpClient;
import HttpClientsImplementation.RestAssured;

public class RequestExecutor {

    private static final String ERROR_MESSAGE = "Something went wrong => RequestExecutor)";

    public RequestExecutor() {
    }

    @SuppressWarnings("unchecked")
    public <T> T executeAndGetResponse(HttpClients httpClients, HttpMethods httpMethods, RequestData requestData) {
        switch (httpClients) {
            case RestAssured:
                switch (httpMethods) {
                    case GET: {
                        return (T) RestAssured.requestGet(requestData);
                    }
                    case PUT: {
                        return (T) RestAssured.requestPut(requestData);
                    }
                    default:
                        throw new RuntimeException(ERROR_MESSAGE);
                }
            case ApacheHttpClient: {
                switch (httpMethods) {
                    case GET: {
                        return (T) ApacheHttpClient.requestGet(requestData);
                    }
                    case PUT: {
                        return (T) ApacheHttpClient.requestPut(requestData);
                    }
                    default:
                        throw new RuntimeException(ERROR_MESSAGE);
                }
            }
            default:
                throw new RuntimeException(ERROR_MESSAGE);
        }
    }
}