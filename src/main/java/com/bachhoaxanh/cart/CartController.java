package com.bachhoaxanh.cart;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * REST API Controller skeleton for Cart resources with VARCHAR(50) PK.
 */
@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GET
    @Path("/customer/{customerId}")
    public Response getCartByCustomerId(@PathParam("customerId") String customerId) {
        Cart cart = cartService.getOrCreateCartByCustomerId(customerId);
        return Response.ok(cart).build();
    }

    @POST
    @Path("/{cartId}/items")
    public Response addItem(@PathParam("cartId") String cartId,
                            @QueryParam("productId") String productId,
                            @QueryParam("quantity") @DefaultValue("1.0") double quantity) {
        Cart updatedCart = cartService.addItemToCart(cartId, productId, quantity);
        return Response.ok(updatedCart).build();
    }

    @PUT
    @Path("/{cartId}/items/{itemId}")
    public Response updateItemQuantity(@PathParam("cartId") String cartId,
                                       @PathParam("itemId") Long itemId,
                                       @QueryParam("quantity") double quantity) {
        Cart updatedCart = cartService.updateItemQuantity(cartId, itemId, quantity);
        return Response.ok(updatedCart).build();
    }

    @DELETE
    @Path("/{cartId}/items/{itemId}")
    public Response removeItem(@PathParam("cartId") String cartId,
                               @PathParam("itemId") Long itemId) {
        Cart updatedCart = cartService.removeItemFromCart(cartId, itemId);
        return Response.ok(updatedCart).build();
    }

    @DELETE
    @Path("/{cartId}")
    public Response clearCart(@PathParam("cartId") String cartId) {
        cartService.clearCart(cartId);
        return Response.noContent().build();
    }
}
