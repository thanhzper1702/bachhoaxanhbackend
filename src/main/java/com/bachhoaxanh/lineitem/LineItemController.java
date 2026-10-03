package com.bachhoaxanh.lineitem;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST API Controller skeleton for LineItem resources.
 */
@Path("/line-items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LineItemController {

    private final LineItemService lineItemService;

    public LineItemController(LineItemService lineItemService) {
        this.lineItemService = lineItemService;
    }

    @GET
    @Path("/order/{orderId}")
    public Response getItemsByOrder(@PathParam("orderId") String orderId) {
        List<LineItem> items = lineItemService.getLineItemsByOrderId(orderId);
        return Response.ok(items).build();
    }

    @GET
    @Path("/cart/{cartId}")
    public Response getItemsByCart(@PathParam("cartId") String cartId) {
        List<LineItem> items = lineItemService.getLineItemsByCartId(cartId);
        return Response.ok(items).build();
    }

    @GET
    @Path("/{id}")
    public Response getItemById(@PathParam("id") Long id) {
        return lineItemService.getLineItemById(id)
                .map(item -> Response.ok(item).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createItem(LineItem lineItem) {
        LineItem created = lineItemService.createLineItem(lineItem);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}/quantity")
    public Response updateQuantity(@PathParam("id") Long id, @QueryParam("quantity") Double quantity) {
        LineItem updated = lineItemService.updateQuantity(id, quantity);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteItem(@PathParam("id") Long id) {
        lineItemService.deleteLineItem(id);
        return Response.noContent().build();
    }
}
