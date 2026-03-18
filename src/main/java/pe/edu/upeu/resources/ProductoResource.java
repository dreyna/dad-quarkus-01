package pe.edu.upeu.resources;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pe.edu.upeu.dtos.ProductoRequest;
import pe.edu.upeu.dtos.ProductoResponse;
import pe.edu.upeu.services.ProductoService;

import java.net.URI;
import java.util.List;

@Path("/api/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoResource {
    private final ProductoService productoService;

    public ProductoResource(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GET
    public List<ProductoResponse> listar() {
        return productoService.listar();
    }

    @GET
    @Path("/{id}")
    public ProductoResponse obtenerPorId(@PathParam("id") Long id) {
        return productoService.obtenerPorId(id);
    }

    @GET
    @Path("/buscar")
    public List<ProductoResponse> buscarPorNombre(@QueryParam("nombre") String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return productoService.listar();
        }
        return productoService.buscarPorNombre(nombre);
    }

    @POST
    public Response registrar(@Valid ProductoRequest request) {
        ProductoResponse response = productoService.registrar(request);
        return Response.created(URI.create("/api/productos/" + response.getId()))
                .entity(response)
                .build();
    }

    @PUT
    @Path("/{id}")
    public ProductoResponse actualizar(@PathParam("id") Long id,
                                       @Valid ProductoRequest request) {
        return productoService.actualizar(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        productoService.eliminar(id);
        return Response.noContent().build();
    }
}
