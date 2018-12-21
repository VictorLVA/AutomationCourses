package HttpClientsImplementation.Utils;

import java.io.IOException;

import com.jayway.restassured.response.Response;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class CustomResponse {

    private String responseBody;
    private int responseStatusCode;

    CustomResponse(HttpResponse httpResponse) {
        try {
            if (httpResponse.getEntity() != null) {
                this.responseBody = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (IOException ioEx) {
            System.out.println("Something went wrong => CustomResponse class, constructor");
        }
        this.responseStatusCode = httpResponse.getStatusLine().getStatusCode();
    }

    CustomResponse(Response response) {
        this.responseBody = response.asString();
        this.responseStatusCode = response.statusCode();
    }

    public String getResponseBody() {
        return responseBody;
    }

    public int getResponseStatusCode() {
        return responseStatusCode;
    }
}