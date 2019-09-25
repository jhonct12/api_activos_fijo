package apiActivosFijo.example.resource;

import apiActivosFijo.example.model.Tipo;
import apiActivosFijo.example.service.TipoService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static apiActivosFijo.example.Diccionario.Diccionario.CODIGO;
import static apiActivosFijo.example.Diccionario.Diccionario.DESCRIPCION;
import static apiActivosFijo.example.Diccionario.Diccionario.KEY_TIPO;

/**
 * The type Tipo  resource.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/tipo")
public class TipoResource {

    /**
     * The Uri info.
     */
    @Context
    UriInfo uriInfo;

    @EJB
    private TipoService tipoService;

    private Responses responses = new Responses();


    /**
     * Gets all tipo .
     *
     * @param firstResult the first result
     * @param maxResult   the max result
     * @return the all tipo
     */
    @GET
    public Response getAllTipo(@QueryParam("firstResult") Integer firstResult, @QueryParam("maxResult") Integer maxResult) {
        if (firstResult != null && firstResult < 1) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, "firstResult", new String[]{"firstResult"}, new String[]{firstResult.toString()});
        }
        if (maxResult != null && maxResult < 1) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, "maxResult", new String[]{"maxResult"}, new String[]{maxResult.toString()});
        }
        List<Tipo> tipos = tipoService.getAllTipo(firstResult, maxResult);
        return Response.ok(tipos).build();
    }


    /**
     * Gets tipo  by code.
     *
     * @param codigo the codigo
     * @return the tipo  by code
     */
    @GET
    @Path("/byCodigo")
    public Response getTipoByCode(@QueryParam(CODIGO) String codigo) {
        if (codigo.equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_TIPO, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }
        Tipo tipo = tipoService.getTipoByCode(codigo);

        if (tipo == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_TIPO, new String[]{CODIGO, DESCRIPCION}, new String[]{codigo, "No existe ningun registro con el codigo ingresado"});
        }

        return Response.ok(tipo).build();
    }

    /**
     * Create tipo  response.
     *
     * @param tipo the tipo
     * @return the response
     */
    @POST
    public Response createTipo(Tipo tipo) {
        if (tipo.getCodigo().equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_TIPO, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }

        Tipo buscarTipo = tipoService.getTipoByCode(tipo.getCodigo());

        if (buscarTipo != null) {
            return responses.getResponse(Response.Status.FORBIDDEN, KEY_TIPO + "_Existe", new String[]{CODIGO, DESCRIPCION}, new String[]{tipo.getCodigo(), "El codigo del objeto que desea crear ya se encuentra registrado"});
        }
        Tipo createTipo = tipoService.createTipo(tipo);
        return Response.status(Response.Status.CREATED).entity(createTipo).build();
    }

    /**
     * Update tipo  response.
     *
     * @param tipo the tipo
     * @return the response
     */
    @PUT
    public Response updateTipo(Tipo tipo) {
        if (tipo.getCodigo().equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_TIPO, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }

        if (tipoService.getTipoByCode(tipo.getCodigo()) == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_TIPO, new String[]{CODIGO, DESCRIPCION}, new String[]{tipo.getCodigo(), "No existe ningun registro con el codigo ingresado"});
        }

        Tipo tipoToUpdate = tipoService.updateTipo(tipo.getCodigo(), tipo);
        return Response.ok(tipoToUpdate).build();
    }


    /**
     * Delete tipo  response.
     *
     * @param codigo the codigo
     * @return the response
     */
    @DELETE
    public Response deleteTipo(@QueryParam(CODIGO) String codigo) {
        if (codigo.equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_TIPO, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }
        if (tipoService.getTipoByCode(codigo) == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_TIPO, new String[]{CODIGO, DESCRIPCION}, new String[]{codigo, "No existe ningun registro con el codigo ingresado"});
        }
        Tipo tipo = tipoService.deleteTipo(codigo);
        return Response.ok(tipo).build();
    }

}
