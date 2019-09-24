package apiActivosFijo.example.resource;

import apiActivosFijo.example.model.Personas;
import apiActivosFijo.example.service.PersonasService;

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
@Path("/personas")
public class PersonasResource {

    @Context
    UriInfo uriInfo;

    @EJB
    PersonasService personasService;

    private Responses responses = new Responses();

    @GET
    public Response getAllPersonas(@QueryParam(FIRST_RESULT) Integer firstResult, @QueryParam(MAX_RESULT) Integer maxResult) {
        if (firstResult != null && firstResult < 1) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, FIRST_RESULT, new String[]{FIRST_RESULT}, new String[]{firstResult.toString()});
        }
        if (maxResult != null && maxResult < 1) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, MAX_RESULT, new String[]{MAX_RESULT}, new String[]{maxResult.toString()});
        }
        List<Personas> personasList = personasService.getAllPersonas(firstResult, maxResult);
        return Response.ok(personasList).build();
    }

    @GET
    @Path("/byDocument")
    public Response getAreaByDocumento(@QueryParam(DOCUMENTO) String documento) {
        if (documento.equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_PERSONAS, new String[]{DOCUMENTO, DESCRIPCION}, new String[]{"", "El documento ingresado no puede estar vacio"});
        }

        Personas personas = personasService.getPersonasByDocumento(documento);

        if (personas == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_PERSONAS, new String[]{DOCUMENTO, DESCRIPCION}, new String[]{documento, "No existe ningun registro con el documento ingresado"});
        }

        return Response.ok(personas).build();
    }

    @POST
    public Response createPersonas(Personas personas) {
        if (personas.getDocumento().equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_PERSONAS, new String[]{DOCUMENTO, DESCRIPCION}, new String[]{"", "El documento ingresado no puede estar vacio"});
        }

        Personas buscarPersonas = personasService.getPersonasByDocumento(personas.getDocumento());

        if (buscarPersonas != null) {
            return responses.getResponse(Response.Status.FORBIDDEN, KEY_PERSONAS + "_Existe", new String[]{DOCUMENTO, DESCRIPCION}, new String[]{personas.getDocumento(), "El documento del objeto que desea crear ya se encuentra registrado"});
        }
        Personas createPersonas = personasService.createPersonas(personas);
        return Response.status(Response.Status.CREATED).entity(createPersonas).build();
    }

    @PUT
    public Response updatePersonas(Personas personas) {
        if (personas.getDocumento().equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_PERSONAS, new String[]{DOCUMENTO, DESCRIPCION}, new String[]{"", "El documento ingresado no puede estar vacio"});
        }

        if (personasService.getPersonasByDocumento(personas.getDocumento()) == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_PERSONAS, new String[]{DOCUMENTO, DESCRIPCION}, new String[]{personas.getDocumento(), "No existe ningun registro con el documento ingresado"});
        }

        Personas personasToUpdate = personasService.updatePersonas(personas.getDocumento(), personas);
        return Response.ok(personasToUpdate).build();
    }

    @DELETE
    public Response deletePersonas(@QueryParam(DOCUMENTO) String documento) {
        if (documento.equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_PERSONAS, new String[]{DOCUMENTO, DESCRIPCION}, new String[]{"", "El documento ingresado no puede estar vacio"});
        }
        if (personasService.getPersonasByDocumento(documento) == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_PERSONAS, new String[]{DOCUMENTO, DESCRIPCION}, new String[]{documento, "No existe ningun registro con el documento ingresado"});
        }
        Personas personas = personasService.deletePersonas(documento);
        return Response.ok(personas).build();
    }
}
