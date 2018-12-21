package HttpClientsImplementation.Utils;

public class CustomResponse {

    private String responseBody;
    private int responseStatusCode;

    public CustomResponse(String responseBody, int responseStatusCode) {
        this.responseBody = responseBody;
        this.responseStatusCode = responseStatusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public int getResponseStatusCode() {
        return responseStatusCode;
    }
}