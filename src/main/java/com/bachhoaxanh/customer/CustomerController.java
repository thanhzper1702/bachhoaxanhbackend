package com.bachhoaxanh.customer;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST API Controller skeleton for Customer resources with VARCHAR(50) PK.
 */
@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GET
    public Response getAllCustomers() {
        List<Customer> customers = customerService.getAllActiveCustomers();
        return Response.ok(customers).build();
    }

    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") String id) {
        return customerService.getCustomerById(id)
                .map(cust -> Response.ok(cust).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response registerCustomer(Customer customer) {
        Customer created = customerService.registerCustomer(customer);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") String id, Customer customer) {
        Customer updated = customerService.updateCustomerProfile(id, customer);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") String id) {
        customerService.deactivateCustomer(id);
        return Response.noContent().build();
    }
}
