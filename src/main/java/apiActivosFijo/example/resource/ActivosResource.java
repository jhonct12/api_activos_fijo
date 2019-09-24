package apiActivosFijo.example.resource;

import apiActivosFijo.example.model.Activos;
import apiActivosFijo.example.service.ActivosService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static apiActivosFijo.example.Diccionario.Diccionario.CODIGO;
import static apiActivosFijo.example.Diccionario.Diccionario.DESCRIPCION;
import static apiActivosFijo.example.Diccionario.Diccionario.KEY_ACTIVOS;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/activos")
public class ActivosResource {

    @Context
    UriInfo uriInfo;

    @EJB
    private ActivosService activosService;

    private Responses responses = new Responses();


    @GET
    public Response getAllActivos(@QueryParam("firstResult") Integer firstResult, @QueryParam("maxResult") Integer maxResult) {
        if (firstResult != null && firstResult < 1) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, "firstResult", new String[]{"firstResult"}, new String[]{firstResult.toString()});
        }
        if (maxResult != null && maxResult < 1) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, "maxResult", new String[]{"maxResult"}, new String[]{maxResult.toString()});
        }
        List<Activos> activosList = activosService.getAllActivos(firstResult, maxResult);
        return Response.ok(activosList).build();
    }

    @GET
    @Path("/byCodigo")
    public Response getActivosByCodigo(@QueryParam(CODIGO) String codigo) {
        if (codigo.equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_ACTIVOS, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }
        Activos activos = activosService.getActivosByCodigo(codigo);

        if (activos == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_ACTIVOS, new String[]{CODIGO, DESCRIPCION}, new String[]{codigo, "No existe ningun registro con el codigo ingresado"});
        }

        return Response.ok(activos).build();
    }

    @GET
    @Path("/byCodeTipo")
    public Response getActivosByTipo(@QueryParam("codeTipo") String codeTipo) {
        if (codeTipo.equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_ACTIVOS, new String[]{"tipo", DESCRIPCION}, new String[]{"", "El tipo ingresado no puede estar vacio"});
        }
        List<Activos> allTipo = activosService.getActivosByTipo(codeTipo);

        if (allTipo.isEmpty()) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_ACTIVOS, new String[]{"tipo", DESCRIPCION}, new String[]{codeTipo, "No existe ningun registro con el tipo ingresado"});
        }

        return Response.ok(allTipo).build();
    }

    @GET
    @Path("/byFechaCompra")
    public Response getActivosByFechaCompra(@QueryParam("fechaCompra") Long fechaCompra) {
        if (fechaCompra == null) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_ACTIVOS, new String[]{"fecha_Compra", DESCRIPCION}, new String[]{"", "La fecha compra ingresada no puede estar vacio"});
        }
        List<Activos> allTipo = activosService.getActivosByFechaCompra(fechaCompra);

        if (allTipo.isEmpty()) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_ACTIVOS, new String[]{"fecha_Compra", DESCRIPCION}, new String[]{fechaCompra.toString(), "No existe ningun registro con la fecha compra ingresada"});
        }

        return Response.ok(allTipo).build();
    }

    @GET
    @Path("/bySerial")
    public Response getActivosBySerial(@QueryParam("serial") String serial) {
        if (serial.equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_ACTIVOS, new String[]{"serial", DESCRIPCION}, new String[]{"", "El serial ingresado no puede estar vacio"});
        }
        List<Activos> allTipo = activosService.getActivosBySerial(serial);

        if (allTipo.isEmpty()) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_ACTIVOS, new String[]{"serial", DESCRIPCION}, new String[]{serial, "No existe ningun registro con el serial ingresado"});
        }

        return Response.ok(allTipo).build();
    }

    @POST
    public Response createActivos(Activos activos) {
        if (activos.getCodigo().equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_ACTIVOS, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }

        Activos buscarActivos = activosService.getActivosByCodigo(activos.getCodigo());

        if (buscarActivos != null) {
            return responses.getResponse(Response.Status.FORBIDDEN, KEY_ACTIVOS + "_Existe", new String[]{CODIGO, DESCRIPCION}, new String[]{activos.getCodigo(), "El codigo del objeto que desea crear ya se encuentra registrado"});
        }

        if (activos.getFechaBaja() <= activos.getFechaCompra()) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, KEY_ACTIVOS + "_Fechas", new String[]{CODIGO, "Fecha_baja", "Fecha_Compra", DESCRIPCION}, new String[]{activos.getCodigo(), activos.getFechaBaja().toString(), activos.getFechaCompra().toString(), "la fecha de compra no puede ser inferior o igual a la fecha de baja"});
        }

        Activos createActivos = activosService.createActivos(activos);
        return Response.status(Response.Status.CREATED).entity(createActivos).build();
    }

    @PUT
    public Response updateActivos(Activos activos) {
        if (activos.getCodigo().equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_ACTIVOS, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }

        if (activosService.getActivosByCodigo(activos.getCodigo()) == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_ACTIVOS, new String[]{CODIGO, DESCRIPCION}, new String[]{activos.getCodigo(), "No existe ningun registro con el codigo ingresado"});
        }

        if (activos.getFechaBaja() <= activos.getFechaCompra()) {
            return responses.getResponse(Response.Status.NOT_ACCEPTABLE, KEY_ACTIVOS + "_Fechas", new String[]{CODIGO, "Fecha_baja", "Fecha_Compra", DESCRIPCION}, new String[]{activos.getCodigo(), activos.getFechaBaja().toString(), activos.getFechaCompra().toString(), "la fecha de compra no puede ser inferior o igual a la fecha de baja"});
        }

        Activos activosToUpdate = activosService.updateActivos(activos.getCodigo(), activos);
        return Response.ok(activosToUpdate).build();
    }


    @DELETE
    public Response deletActivos(@QueryParam(CODIGO) String codigo) {
        if (codigo.equals("")) {
            return responses.getResponse(Response.Status.BAD_REQUEST, KEY_ACTIVOS, new String[]{CODIGO, DESCRIPCION}, new String[]{"", "El codigo ingresado no puede estar vacio"});
        }
        if (activosService.getActivosByCodigo(codigo) == null) {
            return responses.getResponse(Response.Status.NOT_FOUND, KEY_ACTIVOS, new String[]{CODIGO, DESCRIPCION}, new String[]{codigo, "No existe ningun registro con el codigo ingresado"});
        }
        Activos activos = activosService.deletActivos(codigo);
        return Response.ok(activos).build();
    }
}
