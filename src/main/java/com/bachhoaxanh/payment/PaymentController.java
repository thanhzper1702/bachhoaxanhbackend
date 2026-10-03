package com.bachhoaxanh.payment;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST API Controller skeleton for Payment resources with VARCHAR(50) PK.
 */
@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GET
    @Path("/order/{orderId}")
    public Response getPaymentsByOrder(@PathParam("orderId") String orderId) {
        List<Payment> payments = paymentService.getPaymentsByOrder(orderId);
        return Response.ok(payments).build();
    }

    @GET
    @Path("/{id}")
    public Response getPaymentById(@PathParam("id") String id) {
        return paymentService.getPaymentById(id)
                .map(p -> Response.ok(p).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response processPayment(Payment payment) {
        Payment created = paymentService.processPayment(payment);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}/status")
    public Response updateStatus(@PathParam("id") String id, @QueryParam("status") PaymentStatus status) {
        Payment updated = paymentService.updatePaymentStatus(id, status);
        return Response.ok(updated).build();
    }
}
