package CollaboratorClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CollaboratorPermissions {

    @JsonProperty("admin")
    private boolean repoAdmin;
    @JsonProperty("push")
    private boolean repoPushRights;
    @JsonProperty("pull")
    private boolean repoPullRights;

    @Override
    public String toString() {
        return "repoAdmin: " + repoAdmin + "\n" +
                "repoPushRights: " + repoPushRights + "\n" +
                "repoPullRights: " + repoPullRights;
    }
}
