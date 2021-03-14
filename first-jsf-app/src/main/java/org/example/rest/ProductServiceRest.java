package org.example.rest;

import org.example.service.ProductDto;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/product")
public interface ProductServiceRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDto> findAll();

    @GET
    @Path("/byCategory/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDto> productsByCategory(@PathParam("id") Long id);

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto findById(@PathParam("id") Long id);

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto findByName(@PathParam("name") String name);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(ProductDto product);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductDto product);

    @DELETE
    @Path("/{id}")
    void deleteById(Long id);
}
