package apiActivosFijo.example.resource;


import apiActivosFijo.example.model.AsociacionAreas;
import apiActivosFijo.example.model.AsociacionPersonas;
import apiActivosFijo.example.service.AsociacionPersonasService;

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
@Path("/asociacionPersonas")
public class AsociacionPersonasResource {

    @Context
    UriInfo uriInfo;

    @EJB
    private AsociacionPersonasService asociacionPersonasService;

    private Responses responses = new Responses();

    @GET
    public Response getAllAsociacionPersonas(@QueryParam(FIRST_RESULT) Integer firstResult, @QueryParam(MAX_RESULT) Integer maxResult) {
        if (firstResult != null && firstResult < 1) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, FIRST_RESULT, new String[]{FIRST_RESULT}, new String[]{firstResult.toString()});
        }
        if (maxResult != null && maxResult < 1) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, MAX_RESULT, new String[]{MAX_RESULT}, new String[]{maxResult.toString()});
        }
        List<AsociacionPersonas> asociacionPersonasList = asociacionPersonasService.getAllAsociacionPersonas(firstResult, maxResult);
        return Response.ok(asociacionPersonasList).build();
    }


    @GET
    @Path("/byCodigo")
    public Response getAsociacionAreasByCodigo(@QueryParam(CODIGO) Long codigo) {
        if (codigo == null) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_ASOCIACION_PERSONAS, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }
        AsociacionPersonas asociacionPersonas = asociacionPersonasService.getAsociacionPersonasByCodigo(codigo);

        if (asociacionPersonas == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_ASOCIACION_AREAS, new String[]{CODIGO, DESCRIPCION}, new String[]{codigo.toString(), "No existe ningun registro con el codigo ingresado"});
        }

        return Response.ok(asociacionPersonas).build();
    }

    @POST
    public Response createAsociacionPersonas(AsociacionPersonas asociacionPersonas) {
        if (asociacionPersonas.getCodigo() == null) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_ASOCIACION_AREAS, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }

        AsociacionPersonas buscarAsociacionPersonas = asociacionPersonasService.getAsociacionPersonasByCodigo(asociacionPersonas.getCodigo());

        if (buscarAsociacionPersonas != null) {
            return responses.getResponse(Response.Status.FORBIDDEN, KEY_ASOCIACION_AREAS + "_Existe", new String[]{CODIGO, DESCRIPCION}, new String[]{asociacionPersonas.getCodigo().toString(), "El codigo del objeto que desea crear ya se encuentra registrado"});
        }

        if (asociacionPersonas.getFechaRetiro() <= asociacionPersonas.getFechaAsignacion()) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, KEY_ASOCIACION_AREAS + "_Fechas", new String[]{CODIGO, "Fecha_Retiro", "Fecha_Asignacion", DESCRIPCION}, new String[]{asociacionPersonas.getCodigo().toString(), asociacionPersonas.getFechaRetiro().toString(), asociacionPersonas.getFechaAsignacion().toString(), "la fecha de asignacion no puede ser inferior o igual a la fecha de retiro"});
        }

        AsociacionPersonas createAsociacionPersonas = asociacionPersonasService.createAsociacionPersonas(asociacionPersonas);
        return Response.status(Response.Status.CREATED).entity(createAsociacionPersonas).build();
    }

    @PUT
    public Response updateAsociacionPersonas(AsociacionPersonas asociacionPersonas) {
        if (asociacionPersonas.getCodigo() == null) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_ASOCIACION_AREAS, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }

        if (asociacionPersonasService.getAsociacionPersonasByCodigo(asociacionPersonas.getCodigo()) == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_ASOCIACION_AREAS, new String[]{CODIGO, DESCRIPCION}, new String[]{asociacionPersonas.getCodigo().toString(), "No existe ningun registro con el codigo ingresado"});
        }

        if (asociacionPersonas.getFechaRetiro() <= asociacionPersonas.getFechaAsignacion()) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, KEY_ASOCIACION_AREAS + "_Fechas", new String[]{CODIGO, "Fecha_Retiro", "Fecha_Asignacion", DESCRIPCION}, new String[]{asociacionPersonas.getCodigo().toString(), asociacionPersonas.getFechaRetiro().toString(), asociacionPersonas.getFechaAsignacion().toString(), "la fecha de asignacion no puede ser inferior o igual a la fecha de retiro"});
        }

        AsociacionPersonas asociacionPersonasToUpdate = asociacionPersonasService.updateAsociacionPersonas(asociacionPersonas.getCodigo(), asociacionPersonas);
        return Response.ok(asociacionPersonasToUpdate).build();
    }

    @DELETE
    public Response deleteAsociacionPersonas(@QueryParam(CODIGO) Long codigo) {
        if (codigo == null) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_ASOCIACION_AREAS, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }
        if (asociacionPersonasService.getAsociacionPersonasByCodigo(codigo) == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_ASOCIACION_AREAS, new String[]{CODIGO, DESCRIPCION}, new String[]{codigo.toString(), "No existe ningun registro con el codigo ingresado"});
        }
        AsociacionPersonas asociacionPersonas = asociacionPersonasService.deletAsociacionPersonas(codigo);
        return Response.ok(asociacionPersonas).build();
    }
}
