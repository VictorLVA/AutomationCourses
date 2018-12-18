package HttpClientsImplementation;

import HttpClientsImplementation.Utils.RequestDataPreparatory;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

public class RestAssured {

    private RestAssured() {
    }

    public static Response requestGet(RequestDataPreparatory requestData) {
        if (requestData.getLogin() == null && requestData.getToken() == null) {
            return given()
                    .when().get(requestData.getURI());
        } else {
            return given()
                    .auth().preemptive().basic(requestData.getLogin(), requestData.getToken())
                    .when().get(requestData.getURI());
        }
    }

    public static Response requestPut(RequestDataPreparatory requestData) {
        return given()
                .auth().preemptive().basic(requestData.getLogin(), requestData.getToken())
                .when().put(requestData.getURI());
    }
}