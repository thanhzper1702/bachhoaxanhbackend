package com.bachhoaxanh.review;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST API Controller skeleton for Review resources.
 */
@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GET
    @Path("/product/{productId}")
    public Response getReviewsByProduct(@PathParam("productId") String productId) {
        List<Review> reviews = reviewService.getReviewsByProduct(productId);
        return Response.ok(reviews).build();
    }

    @GET
    @Path("/{id}")
    public Response getReviewById(@PathParam("id") Long id) {
        return reviewService.getReviewById(id)
                .map(r -> Response.ok(r).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response submitReview(Review review) {
        Review created = reviewService.submitReview(review);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReview(@PathParam("id") Long id) {
        reviewService.deleteReview(id);
        return Response.noContent().build();
    }
}
