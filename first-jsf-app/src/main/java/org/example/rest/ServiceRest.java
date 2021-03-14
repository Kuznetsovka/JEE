package org.example.rest;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/product")
public interface ServiceRest<T> {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<T> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    T findById(@PathParam("id") Long id);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(T entity);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(T entity);

    @DELETE
    @Path("/{id}")
    void deleteById(Long id);
}
