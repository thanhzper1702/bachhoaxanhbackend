package com.bachhoaxanh.supplier;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST API Controller skeleton for Supplier resources with VARCHAR(50) PK.
 */
@Path("/suppliers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GET
    public Response getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        return Response.ok(suppliers).build();
    }

    @GET
    @Path("/{id}")
    public Response getSupplierById(@PathParam("id") String id) {
        return supplierService.getSupplierById(id)
                .map(s -> Response.ok(s).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createSupplier(Supplier supplier) {
        Supplier created = supplierService.createSupplier(supplier);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateSupplier(@PathParam("id") String id, Supplier supplier) {
        Supplier updated = supplierService.updateSupplier(id, supplier);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSupplier(@PathParam("id") String id) {
        supplierService.deleteSupplier(id);
        return Response.noContent().build();
    }
}
