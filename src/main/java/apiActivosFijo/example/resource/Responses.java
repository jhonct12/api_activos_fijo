package apiActivosFijo.example.resource;


import apiActivosFijo.example.model.ErrorModel;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

public class Responses {
    /**
     * Custom response depending on the given status and description
     *
     * @param status {@link Response.Status} to be showed
     * @param id     Id of the {@link ErrorModel}
     * @param keys   {@link String} array with the possible keys
     * @param values {@link String} array with the possible values of the previous keys
     * @return {@link Response} with the given status code and a body with the a {@link ErrorModel}
     */
    Response getResponse(Response.Status status, String id, String[] keys, String[] values) {
        Map<String, String> description = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            description.put(keys[i], values[i]);
        }
        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode(status.getStatusCode());
        errorModel.setDescription(description);
        errorModel.setId(id);
        return Response.status(status).entity(errorModel).build();
    }
}
