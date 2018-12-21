package HttpClientsImplementation.Utils;

import Constants.HttpClients;
import Constants.HttpMethods;
import HttpClientsImplementation.ApacheHttpClient;
import HttpClientsImplementation.RestAssured;

public class CustomResponseBuilder {

    private static final String ERROR_MESSAGE = "Something went wrong => CustomResponseBuilder)";

    public CustomResponseBuilder() {
    }

    public CustomResponse getCustomResponse(HttpClients httpClients, HttpMethods httpMethods, RequestData requestData) {
        switch (httpClients) {
            case RestAssured: {
                RestAssured restAssuredClient = new RestAssured();
                return new CustomResponse(restAssuredClient.doRequest(httpMethods, requestData));
            }
            case ApacheHttpClient: {
                ApacheHttpClient apacheHttpClient = new ApacheHttpClient();
                return new CustomResponse(apacheHttpClient.doRequest(httpMethods, requestData));
            }
            default:
                throw new RuntimeException(ERROR_MESSAGE);
        }
    }
}