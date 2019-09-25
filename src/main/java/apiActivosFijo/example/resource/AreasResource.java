package apiActivosFijo.example.resource;

import apiActivosFijo.example.model.Areas;

import apiActivosFijo.example.service.AreasService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static apiActivosFijo.example.Diccionario.Diccionario.CODIGO;
import static apiActivosFijo.example.Diccionario.Diccionario.DESCRIPCION;
import static apiActivosFijo.example.Diccionario.Diccionario.KEY_AREA;

/**
 * The type Areas resource.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/areas")
public class AreasResource {

    /**
     * The Uri info.
     */
    @Context
    UriInfo uriInfo;

    @EJB
    private AreasService areasService;

    private Responses responses = new Responses();


    /**
     * Gets all areas.
     *
     * @param firstResult the first result
     * @param maxResult   the max result
     * @return the all areas
     */
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

    /**
     * Gets area by codigo.
     *
     * @param codigo the codigo
     * @return the area by codigo
     */
    @GET
    @Path("/byCodigo")
    public Response getAreaByCodigo(@QueryParam(CODIGO) String codigo) {
        if (codigo.equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_AREA, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }

        Areas areas = areasService.getAreasByCodigo(codigo);

        if (areas == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_AREA, new String[]{CODIGO, DESCRIPCION}, new String[]{codigo, "No existe ningun registro con el codigo ingresado"});
        }

        return Response.ok(areas).build();
    }

    /**
     * Create areas response.
     *
     * @param areas the areas
     * @return the response
     */
    @POST
    public Response createAreas(Areas areas) {
        if (areas.getCodigo().equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_AREA, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }

        Areas buscarAreas = areasService.getAreasByCodigo(areas.getCodigo());

        if (buscarAreas != null) {
            return responses.getResponse(Response.Status.FORBIDDEN, KEY_AREA + "_Existe", new String[]{CODIGO, DESCRIPCION}, new String[]{areas.getCodigo(), "El codigo del objeto que desea crear ya se encuentra registrado"});
        }
        Areas createAreas = areasService.createAreas(areas);
        return Response.status(Response.Status.CREATED).entity(createAreas).build();
    }

    /**
     * Update areas response.
     *
     * @param areas the areas
     * @return the response
     */
    @PUT
    public Response updateAreas(Areas areas) {
        if (areas.getCodigo().equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_AREA, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }

        if (areasService.getAreasByCodigo(areas.getCodigo()) == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_AREA, new String[]{CODIGO, DESCRIPCION}, new String[]{areas.getCodigo(), "No existe ningun registro con el codigo ingresado"});
        }

        Areas areasToUpdate = areasService.updateAreas(areas.getCodigo(), areas);
        return Response.ok(areasToUpdate).build();
    }

    /**
     * Delete areas response.
     *
     * @param codigo the codigo
     * @return the response
     */
    @DELETE
    public Response deleteAreas(@QueryParam(CODIGO) String codigo) {
        if (codigo.equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_AREA, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }
        if (areasService.getAreasByCodigo(codigo) == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_AREA, new String[]{CODIGO, DESCRIPCION}, new String[]{codigo, "No existe ningun registro con el codigo ingresado"});
        }
        Areas areas = areasService.deleteAreas(codigo);
        return Response.ok(areas).build();
    }

}
