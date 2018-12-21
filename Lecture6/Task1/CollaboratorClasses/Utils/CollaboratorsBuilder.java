package CollaboratorClasses.Utils;

import java.io.IOException;

import CollaboratorClasses.Collaborator;
import HttpClientsImplementation.Utils.CustomResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CollaboratorsBuilder {

    private CollaboratorsBuilder() {
    }

    public static Collaborator[] createCollaborators(CustomResponse responseCollaborators) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(responseCollaborators.getResponseBody(), Collaborator[].class);
        } catch (IOException ioEx) {
            System.out.println("Something went wrong => CollaboratorsBuilder class, method 'createCollaborators'");
            return new Collaborator[0];
        }
    }
}