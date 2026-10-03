package com.bachhoaxanh.address;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST API Controller skeleton for Address resources.
 */
@Path("/addresses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GET
    @Path("/customer/{customerId}")
    public Response getAddressesByCustomer(@PathParam("customerId") String customerId) {
        List<Address> addresses = addressService.getAddressesByCustomer(customerId);
        return Response.ok(addresses).build();
    }

    @GET
    @Path("/{id}")
    public Response getAddressById(@PathParam("id") Long id) {
        return addressService.getAddressById(id)
                .map(a -> Response.ok(a).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response addAddress(Address address) {
        Address created = addressService.addAddress(address);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAddress(@PathParam("id") Long id, Address address) {
        Address updated = addressService.updateAddress(id, address);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAddress(@PathParam("id") Long id) {
        addressService.deleteAddress(id);
        return Response.noContent().build();
    }
}
