package CollaboratorClasses.Utils;

import java.io.IOException;

import CollaboratorClasses.Collaborator;
import HttpClientsImplementation.Utils.CustomResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CollaboratorsBuilder {

    private static final String ERROR_MESSAGE = "Something went wrong (CollaboratorsBuilder => IOException)";

    private static ObjectMapper objectMapper = new ObjectMapper();

    private CollaboratorsBuilder() {
    }

    public static Collaborator[] createCollaborators(CustomResponse responseCollaborators) {
        try {
            return objectMapper.readValue(responseCollaborators.getResponseBody(), Collaborator[].class);
        } catch (IOException ioEx) {
            System.out.println(ERROR_MESSAGE);
            return new Collaborator[0];
        }
    }
}