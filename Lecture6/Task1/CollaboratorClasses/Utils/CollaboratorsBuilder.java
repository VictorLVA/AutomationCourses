package CollaboratorClasses.Utils;

import java.io.IOException;

import CollaboratorClasses.Collaborator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.response.Response;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class CollaboratorsBuilder {

    private static final String ERROR_MESSAGE = "Something went wrong (CollaboratorsBuilder => IOException)";

    private static ObjectMapper objectMapper = new ObjectMapper();

    private CollaboratorsBuilder() {
    }

    public static Collaborator[] createCollaborators(Response responseCollaborators) {
        try {
            return objectMapper.readValue(responseCollaborators.asString(), Collaborator[].class);
        } catch (IOException ioEx) {
            System.out.println(ERROR_MESSAGE);
            return new Collaborator[0];
        }
    }

    public static Collaborator[] createCollaborators(HttpResponse responseCollaborators) {
        try {
            String responseCollaboratorsBodyJSON = EntityUtils.toString(responseCollaborators.getEntity());
            return objectMapper.readValue(responseCollaboratorsBodyJSON, Collaborator[].class);
        } catch (IOException ioEx) {
            System.out.println(ERROR_MESSAGE);
            return new Collaborator[0];
        }
    }
}