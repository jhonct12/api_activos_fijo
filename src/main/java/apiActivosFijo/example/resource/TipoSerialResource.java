package apiActivosFijo.example.resource;

import apiActivosFijo.example.model.Tipo;
import apiActivosFijo.example.service.TipoSerialService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static apiActivosFijo.example.Diccionario.Diccionario.CODIGO;
import static apiActivosFijo.example.Diccionario.Diccionario.DESCRIPCION;
import static apiActivosFijo.example.Diccionario.Diccionario.KEY_TIPO_SERIAl;

/**
 * The type Tipo serial resource.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/tipoSerial")
public class TipoSerialResource {

    /**
     * The Uri info.
     */
    @Context
    UriInfo uriInfo;

    @EJB
    private TipoSerialService tipoSerialService;

    private Responses responses = new Responses();


    /**
     * Gets all tipo serial.
     *
     * @param firstResult the first result
     * @param maxResult   the max result
     * @return the all tipo serial
     */
    @GET
    public Response getAllTipoSerial(@QueryParam("firstResult") Integer firstResult, @QueryParam("maxResult") Integer maxResult) {
        if (firstResult != null && firstResult < 1) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, "firstResult", new String[]{"firstResult"}, new String[]{firstResult.toString()});
        }
        if (maxResult != null && maxResult < 1) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, "maxResult", new String[]{"maxResult"}, new String[]{maxResult.toString()});
        }
        List<Tipo> tipoSerials = tipoSerialService.getAllTipoSerial(firstResult, maxResult);
        return Response.ok(tipoSerials).build();
    }


    /**
     * Gets tipo serial by code.
     *
     * @param codigo the codigo
     * @return the tipo serial by code
     */
    @GET
    @Path("/byCodigo")
    public Response getTipoSerialByCode(@QueryParam(CODIGO) String codigo) {
        if (codigo.equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_TIPO_SERIAl, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }
        Tipo tipoSerial = tipoSerialService.getTipoSerialByCode(codigo);

        if (tipoSerial == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_TIPO_SERIAl, new String[]{CODIGO, DESCRIPCION}, new String[]{codigo, "No existe ningun registro con el codigo ingresado"});
        }

        return Response.ok(tipoSerial).build();
    }

    /**
     * Create tipo serial response.
     *
     * @param tipoSerial the tipo serial
     * @return the response
     */
    @POST
    public Response createTipoSerial(Tipo tipoSerial) {
        if (tipoSerial.getCodigo().equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_TIPO_SERIAl, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }

        Tipo buscarTipoSerial = tipoSerialService.getTipoSerialByCode(tipoSerial.getCodigo());

        if (buscarTipoSerial != null) {
            return responses.getResponse(Response.Status.FORBIDDEN, KEY_TIPO_SERIAl + "_Existe", new String[]{CODIGO, DESCRIPCION}, new String[]{tipoSerial.getCodigo(), "El codigo del objeto que desea crear ya se encuentra registrado"});
        }
        Tipo createTipoSerial = tipoSerialService.createTipoSerial(tipoSerial);
        return Response.status(Response.Status.CREATED).entity(createTipoSerial).build();
    }

    /**
     * Update tipo serial response.
     *
     * @param tipoSerial the tipo serial
     * @return the response
     */
    @PUT
    public Response updateTipoSerial(Tipo tipoSerial) {
        if (tipoSerial.getCodigo().equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_TIPO_SERIAl, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }

        if (tipoSerialService.getTipoSerialByCode(tipoSerial.getCodigo()) == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_TIPO_SERIAl, new String[]{CODIGO, DESCRIPCION}, new String[]{tipoSerial.getCodigo(), "No existe ningun registro con el codigo ingresado"});
        }

        Tipo tipoSerialToUpdate = tipoSerialService.updateTipoSerial(tipoSerial.getCodigo(), tipoSerial);
        return Response.ok(tipoSerialToUpdate).build();
    }


    /**
     * Delete tipo serial response.
     *
     * @param codigo the codigo
     * @return the response
     */
    @DELETE
    public Response deleteTipoSerial(@QueryParam(CODIGO) String codigo) {
        if (codigo.equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_TIPO_SERIAl, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }
        if (tipoSerialService.getTipoSerialByCode(codigo) == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_TIPO_SERIAl, new String[]{CODIGO, DESCRIPCION}, new String[]{codigo, "No existe ningun registro con el codigo ingresado"});
        }
        Tipo tipoSerial = tipoSerialService.deleteTipoSerial(codigo);
        return Response.ok(tipoSerial).build();
    }

}
