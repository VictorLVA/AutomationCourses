package HttpClientsImplementation;

import Constants.HttpMethods;
import HttpClientsImplementation.Utils.RequestData;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

public class RestAssured {

    public RestAssured() {
    }

    public Response doRequest(HttpMethods httpMethods, RequestData requestData) {
        switch (httpMethods) {
            case GET: {
                return requestGet(requestData);
            }
            case PUT: {
                return requestPut(requestData);
            }
            default:
                throw new RuntimeException("Something went wrong => RestAssured class, method 'doRequest'");
        }
    }

    private Response requestGet(RequestData requestData) {
        if (isContainAuthData(requestData)) {
            return given()
                    .auth().preemptive().basic(requestData.getLogin(), requestData.getToken())
                    .when().get(requestData.getURI());
        } else {
            return given()
                    .when().get(requestData.getURI());
        }
    }

    private Response requestPut(RequestData requestData) {
        if (isContainAuthData(requestData)) {
            return given()
                    .auth().preemptive().basic(requestData.getLogin(), requestData.getToken())
                    .when().put(requestData.getURI());
        } else {
            return given()
                    .when().put(requestData.getURI());
        }
    }

    private boolean isContainAuthData(RequestData requestData) {
        return (requestData.getLogin() != null && requestData.getToken() != null);
    }
}