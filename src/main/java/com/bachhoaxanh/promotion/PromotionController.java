package com.bachhoaxanh.promotion;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST API Controller skeleton for Promotion resources with VARCHAR(50) PK.
 */
@Path("/promotions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GET
    public Response getAllPromotions() {
        List<Promotion> promotions = promotionService.getAllActivePromotions();
        return Response.ok(promotions).build();
    }

    @GET
    @Path("/{id}")
    public Response getPromotionById(@PathParam("id") String id) {
        return promotionService.getPromotionById(id)
                .map(p -> Response.ok(p).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/code/{code}")
    public Response getPromotionByCode(@PathParam("code") String code) {
        return promotionService.getPromotionByName(code)
                .map(p -> Response.ok(p).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createPromotion(Promotion promotion) {
        Promotion created = promotionService.createPromotion(promotion);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePromotion(@PathParam("id") String id) {
        promotionService.deletePromotion(id);
        return Response.noContent().build();
    }
}
