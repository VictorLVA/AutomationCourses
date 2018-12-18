package HttpClientsImplementation.Utils;

public class RequestDataPreparatory {
    private String URI;
    private String login;
    private String token;

    public RequestDataPreparatory(String URI) {
        this.URI = URI;
    }

    public RequestDataPreparatory(String URI, String login, String token) {
        this.URI = URI;
        this.login = login;
        this.token = token;
    }

    public String getURI() {
        return URI;
    }

    public String getLogin() {
        return login;
    }

    public String getToken() {
        return token;
    }
}