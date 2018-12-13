package Helper;

import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

public class RequestsRestAssured {

    private RequestsRestAssured() {
    }

    public static Response requestGet(String URI) {
        return given().when().get(URI);
    }

    public static Response requestGet(String URI, String login, String token) {
        return given().auth().preemptive().basic(login, token).when().get(URI);
    }

    public static Response requestPut(String URI, String login, String token) {
        return given().auth().preemptive().basic(login, token).when().put(URI);
    }
}