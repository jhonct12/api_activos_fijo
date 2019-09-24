package apiActivosFijo.example.resource;

import apiActivosFijo.example.model.AsociacionAreas;
import apiActivosFijo.example.service.AsociacionAreasService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static apiActivosFijo.example.Diccionario.Diccionario.*;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/asociacionAreas")
public class AsociacionAreasResource {

    @Context
    UriInfo uriInfo;

    @EJB
    private AsociacionAreasService asociacionAreasService;

    private Responses responses = new Responses();


    @GET
    public Response getAllAsociacionAreas(@QueryParam(FIRST_RESULT) Integer firstResult, @QueryParam(MAX_RESULT) Integer maxResult) {
        if (firstResult != null && firstResult < 1) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, FIRST_RESULT, new String[]{FIRST_RESULT}, new String[]{firstResult.toString()});
        }
        if (maxResult != null && maxResult < 1) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, MAX_RESULT, new String[]{MAX_RESULT}, new String[]{maxResult.toString()});
        }
        List<AsociacionAreas> asociacionAreasList = asociacionAreasService.getAllAsociacionAreas(firstResult, maxResult);
        return Response.ok(asociacionAreasList).build();
    }

    @GET
    @Path("/byCodigo")
    public Response getAsociacionAreasByCodigo(@QueryParam(CODIGO) Long codigo) {
        if (codigo == null) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_ASOCIACION_AREAS, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }
        AsociacionAreas asociacionAreas = asociacionAreasService.getAsociacionAreasByCodigo(codigo);

        if (asociacionAreas == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_ASOCIACION_AREAS, new String[]{CODIGO, DESCRIPCION}, new String[]{codigo.toString(), "No existe ningun registro con el codigo ingresado"});
        }

        return Response.ok(asociacionAreas).build();
    }


    @POST
    public Response createAsociacionAreas(AsociacionAreas asociacionAreas) {
        if (asociacionAreas.getCodigo() == null) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_ASOCIACION_AREAS, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }

        AsociacionAreas buscarAsociacionAreas = asociacionAreasService.getAsociacionAreasByCodigo(asociacionAreas.getCodigo());

        if (buscarAsociacionAreas != null) {
            return responses.getResponse(Response.Status.FORBIDDEN, KEY_ASOCIACION_AREAS + "_Existe", new String[]{CODIGO, DESCRIPCION}, new String[]{asociacionAreas.getCodigo().toString(), "El codigo del objeto que desea crear ya se encuentra registrado"});
        }

        if (asociacionAreas.getFechaRetiro() <= asociacionAreas.getFechaAsignacion()) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, KEY_ASOCIACION_AREAS + "_Fechas", new String[]{CODIGO, "Fecha_Retiro", "Fecha_Asignacion", DESCRIPCION}, new String[]{asociacionAreas.getCodigo().toString(), asociacionAreas.getFechaRetiro().toString(), asociacionAreas.getFechaAsignacion().toString(), "la fecha de asignacion no puede ser inferior o igual a la fecha de retiro"});
        }

        AsociacionAreas createAsociacionAreas = asociacionAreasService.createAsociacionAreas(asociacionAreas);
        return Response.status(Response.Status.CREATED).entity(createAsociacionAreas).build();
    }

    @PUT
    public Response updateAsociacionAreas(AsociacionAreas asociacionAreas) {
        if (asociacionAreas.getCodigo() == null) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_ASOCIACION_AREAS, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }

        if (asociacionAreasService.getAsociacionAreasByCodigo(asociacionAreas.getCodigo()) == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_ASOCIACION_AREAS, new String[]{CODIGO, DESCRIPCION}, new String[]{asociacionAreas.getCodigo().toString(), "No existe ningun registro con el codigo ingresado"});
        }

        if (asociacionAreas.getFechaRetiro() <= asociacionAreas.getFechaAsignacion()) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, KEY_ASOCIACION_AREAS + "_Fechas", new String[]{CODIGO, "Fecha_Retiro", "Fecha_Asignacion", DESCRIPCION}, new String[]{asociacionAreas.getCodigo().toString(), asociacionAreas.getFechaRetiro().toString(), asociacionAreas.getFechaAsignacion().toString(), "la fecha de asignacion no puede ser inferior o igual a la fecha de retiro"});
        }

        AsociacionAreas asociacionAreasToUpdate = asociacionAreasService.updateAsociacionAreas(asociacionAreas.getCodigo(), asociacionAreas);
        return Response.ok(asociacionAreasToUpdate).build();
    }

    @DELETE
    public Response deleteAsociacionAreas(@QueryParam(CODIGO) Long codigo) {
        if (codigo == null) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_ASOCIACION_AREAS, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }
        if (asociacionAreasService.getAsociacionAreasByCodigo(codigo) == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_ASOCIACION_AREAS, new String[]{CODIGO, DESCRIPCION}, new String[]{codigo.toString(), "No existe ningun registro con el codigo ingresado"});
        }
        AsociacionAreas asociacionAreas = asociacionAreasService.deletAsociacionAreas(codigo);
        return Response.ok(asociacionAreas).build();
    }
}
