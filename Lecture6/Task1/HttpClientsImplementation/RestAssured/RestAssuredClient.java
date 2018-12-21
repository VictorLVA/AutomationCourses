package HttpClientsImplementation.RestAssured;

import Constants.HttpMethods;
import HttpClientsImplementation.CustomHttpClient;
import HttpClientsImplementation.Utils.CustomResponse;
import HttpClientsImplementation.Utils.RequestData;
import com.jayway.restassured.response.Response;

public class RestAssuredClient implements CustomHttpClient {

    public RestAssuredClient() {
    }

    @Override
    public CustomResponse doRequest(HttpMethods httpMethods, RequestData requestData) {
        RestAssured restAssured = new RestAssured();
        Response restAssuredResponse = restAssured.doRequest(httpMethods, requestData);
        String responseBody = restAssuredResponse.asString();
        int statusCode = restAssuredResponse.statusCode();
        return new CustomResponse(responseBody, statusCode);
    }
}