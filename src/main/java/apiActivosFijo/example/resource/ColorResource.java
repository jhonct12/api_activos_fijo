package apiActivosFijo.example.resource;

import apiActivosFijo.example.model.Color;
import apiActivosFijo.example.service.ColorService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/color")
public class ColorResource {

    @Context
    UriInfo uriInfo;

    @EJB
    private ColorService colorService;

    private Responses responses = new Responses();

    private static final String CODE = "code";
    private static final String KEY_COLOR = "key_color";

    @GET
    public Response getAllColor(@QueryParam("firstResult") Integer firstResult, @QueryParam("maxResult") Integer maxResult) {
        if (firstResult != null && firstResult < 1) {
            return responses.getResponse(Response.Status.BAD_REQUEST, "firstResult", new String[]{"firstResult"}, new String[]{firstResult.toString()});
        }
        if (maxResult != null && maxResult < 1) {
            return responses.getResponse(Response.Status.BAD_REQUEST, "maxResult", new String[]{"maxResult"}, new String[]{maxResult.toString()});
        }
        List<Color> colorList = colorService.getAllColor(firstResult, maxResult);
        return Response.ok(colorList).build();
    }

    @GET
    @Path("/byCode")
    public Response getColorByCode(@QueryParam("code") String code){
        if (code.equals("")){
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_COLOR, new String[]{CODE}, new String[]{""});
        }
        Color color = colorService.getColorByCode(code);

        if (color == null){
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_COLOR, new String[]{CODE}, new String[]{code});
        }

        return Response.ok(color).build();
    }

    @POST
    public Response createColor(Color color){
        if (color.getCode().equals("")){
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_COLOR, new String[]{CODE}, new String[]{""});
        }

        Color buscarColor = colorService.getColorByCode(color.getCode());

        if (buscarColor != null){
            return responses.getResponse(Response.Status.FORBIDDEN, KEY_COLOR+"_Existe", new String[]{CODE}, new String[]{color.getCode()});
        }
        Color createColor = colorService.createColor(color);
        return Response.status(Response.Status.CREATED).entity(createColor).build();
    }

    @PUT
    public Response updateColor(Color color){
        if (color.getCode().equals("")){
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_COLOR, new String[]{CODE}, new String[]{""});
        }

        if (colorService.getColorByCode(color.getCode()) == null){
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_COLOR, new String[]{CODE}, new String[]{color.getCode()});
        }

        Color colorToUpdate = colorService.updateColor(color.getCode(), color);
        return Response.ok(colorToUpdate).build();
    }

    @DELETE
    public Response deleteColor(@QueryParam("code") String code){
        if (code.equals("")){
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_COLOR, new String[]{CODE}, new String[]{""});
        }
        if (colorService.getColorByCode(code) == null){
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_COLOR, new String[]{CODE}, new String[]{code});
        }
        Color color = colorService.deleteColor(code);
        return Response.ok(color).build();
    }

}
