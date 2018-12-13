package CollaboratorClasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Collaborator {

    private String login;
    private int id;
    private String type;
    @JsonProperty("site_admin")
    private boolean siteAdmin;
    private CollaboratorPermissions collaboratorPermissions;

    public void setPermissions(CollaboratorPermissions collaboratorPermissions) {
        this.collaboratorPermissions = collaboratorPermissions;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "login: " + login + "\n" +
                "id: " + id + "\n" +
                "type: " + type + "\n" +
                "siteAdmin: " + siteAdmin + "\n" +
                collaboratorPermissions;
    }
}