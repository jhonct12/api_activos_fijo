package apiActivosFijo.example.resource;

import apiActivosFijo.example.model.Color;
import apiActivosFijo.example.service.ColorService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

import static apiActivosFijo.example.Diccionario.Diccionario.*;

/**
 * The type Color resource.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/color")
public class ColorResource {

    /**
     * The Uri info.
     */
    @Context
    UriInfo uriInfo;

    @EJB
    private ColorService colorService;

    private Responses responses = new Responses();


    /**
     * Gets all color.
     *
     * @param firstResult the first result
     * @param maxResult   the max result
     * @return the all color
     */
    @GET
    public Response getAllColor(@QueryParam(FIRST_RESULT) Integer firstResult, @QueryParam(MAX_RESULT) Integer maxResult) {
        if (firstResult != null && firstResult < 1) {
            return responses.getResponse(Response.Status.BAD_REQUEST, FIRST_RESULT, new String[]{FIRST_RESULT, DESCRIPCION}, new String[]{firstResult.toString(), "El primer resultado no puede ser menor a 1"});
        }
        if (maxResult != null && maxResult < 1) {
            return responses.getResponse(Response.Status.BAD_REQUEST, MAX_RESULT, new String[]{MAX_RESULT, DESCRIPCION}, new String[]{maxResult.toString(), "El maximo resultado no puede ser menor a 1"});
        }
        List<Color> colorList = colorService.getAllColor(firstResult, maxResult);
        return Response.ok(colorList).build();
    }

    /**
     * Gets color by codigo.
     *
     * @param codigo the codigo
     * @return the color by codigo
     */
    @GET
    @Path("/byCodigo")
    public Response getColorByCodigo(@QueryParam(CODIGO) String codigo) {
        if (codigo.equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_COLOR, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }
        Color color = colorService.getColorByCodigo(codigo);

        if (color == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_COLOR, new String[]{CODIGO, DESCRIPCION}, new String[]{codigo, "No existe ningun registro con el codigo ingresado"});
        }

        return Response.ok(color).build();
    }

    /**
     * Create color response.
     *
     * @param color the color
     * @return the response
     */
    @POST
    public Response createColor(Color color) {
        if (color.getCodigo().equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_COLOR, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }

        Color buscarColor = colorService.getColorByCodigo(color.getCodigo());

        if (buscarColor != null) {
            return responses.getResponse(Response.Status.FORBIDDEN, KEY_COLOR + "_Existe", new String[]{CODIGO, DESCRIPCION}, new String[]{color.getCodigo(), "El codigo del objeto que desea crear ya se encuentra registrado"});
        }
        Color createColor = colorService.createColor(color);
        return Response.status(Response.Status.CREATED).entity(createColor).build();
    }

    /**
     * Update color response.
     *
     * @param color the color
     * @return the response
     */
    @PUT
    public Response updateColor(Color color) {
        if (color.getCodigo().equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_COLOR, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }

        if (colorService.getColorByCodigo(color.getCodigo()) == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_COLOR, new String[]{CODIGO, DESCRIPCION}, new String[]{color.getCodigo(), "No existe ningun registro con el codigo ingresado"});
        }

        Color colorToUpdate = colorService.updateColor(color.getCodigo(), color);
        return Response.ok(colorToUpdate).build();
    }

    /**
     * Delete color response.
     *
     * @param codigo the codigo
     * @return the response
     */
    @DELETE
    public Response deleteColor(@QueryParam(CODIGO) String codigo) {
        if (codigo.equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_COLOR, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }
        if (colorService.getColorByCodigo(codigo) == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_COLOR, new String[]{CODIGO, DESCRIPCION}, new String[]{codigo, "No existe ningun registro con el codigo ingresado"});
        }
        Color color = colorService.deleteColor(codigo);
        return Response.ok(color).build();
    }

}
