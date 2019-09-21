package apiActivosFijo.example.resource;

import apiActivosFijo.example.model.EstadoActual;
import apiActivosFijo.example.model.TipoSerial;
import apiActivosFijo.example.service.EstadoActualService;
import apiActivosFijo.example.service.TipoSerialService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/estadoActual")
public class EstadoActualResource {

    @Context
    UriInfo uriInfo;

    @EJB
    private EstadoActualService estadoActualService;

    private Responses responses = new Responses();


    @GET
    public Response getAllEstadoActual(@QueryParam("firstResult") Integer firstResult, @QueryParam("maxResult") Integer maxResult) {
        if (firstResult != null && firstResult < 1) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, "firstResult", new String[]{"firstResult"}, new String[]{firstResult.toString()});
        }
        if (maxResult != null && maxResult < 1) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, "maxResult", new String[]{"maxResult"}, new String[]{maxResult.toString()});
        }
        List<EstadoActual> estadoActuals = estadoActualService.getAllEstadoActual(firstResult, maxResult);
        return Response.ok(estadoActuals).build();
    }
}
