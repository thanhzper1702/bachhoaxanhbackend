package com.bachhoaxanh.order;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST API Controller skeleton for Order resources with VARCHAR(50) PK.
 */
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GET
    public Response getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return Response.ok(orders).build();
    }

    @GET
    @Path("/{id}")
    public Response getOrderById(@PathParam("id") String id) {
        return orderService.getOrderById(id)
                .map(o -> Response.ok(o).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/customer/{customerId}")
    public Response getOrdersByCustomer(@PathParam("customerId") String customerId) {
        List<Order> orders = orderService.getOrdersByCustomer(customerId);
        return Response.ok(orders).build();
    }

    @POST
    public Response createOrder(Order order) {
        Order created = orderService.createOrder(order);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}/pay")
    public Response markAsPaid(@PathParam("id") String id) {
        Order updated = orderService.markAsPaid(id);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response cancelOrder(@PathParam("id") String id) {
        orderService.cancelOrder(id);
        return Response.noContent().build();
    }
}
