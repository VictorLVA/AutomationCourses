package Helper;

import java.io.IOException;

import CollaboratorClasses.Collaborator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.response.Response;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class CollaboratorsBuilder {

    private CollaboratorsBuilder() {
    }

    public static Collaborator[] createCollaborators(Response responseCollaborators) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseCollaborators.asString(), Collaborator[].class);
    }

    public static Collaborator[] createCollaborators(HttpResponse responseCollaborators) throws IOException {
        String responseCollaboratorsBodyJSON = EntityUtils.toString(responseCollaborators.getEntity());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseCollaboratorsBodyJSON, Collaborator[].class);
    }
}