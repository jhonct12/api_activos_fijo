package apiActivosFijo.example.resource;

import apiActivosFijo.example.model.Areas;
import apiActivosFijo.example.model.Color;
import apiActivosFijo.example.service.AreasService;
import apiActivosFijo.example.service.ColorService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/areas")
public class AreasResource {

    @Context
    UriInfo uriInfo;

    @EJB
    private AreasService areasService;

    private Responses responses = new Responses();


    @GET
    public Response getAllAreas(@QueryParam("firstResult") Integer firstResult, @QueryParam("maxResult") Integer maxResult) {
        if (firstResult != null && firstResult < 1) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, "firstResult", new String[]{"firstResult"}, new String[]{firstResult.toString()});
        }
        if (maxResult != null && maxResult < 1) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, "maxResult", new String[]{"maxResult"}, new String[]{maxResult.toString()});
        }
        List<Areas> areasList = areasService.getAllAreas(firstResult, maxResult);
        return Response.ok(areasList).build();
    }
}
