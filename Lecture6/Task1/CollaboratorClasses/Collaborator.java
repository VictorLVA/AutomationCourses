package CollaboratorClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Collaborator {

    @JsonProperty("login")
    private String login;
    @JsonProperty("id")
    private int id;
    @JsonProperty("type")
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

