package com.bachhoaxanh.product;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST API Controller skeleton for Product resources with VARCHAR(50) PK.
 */
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GET
    public Response getAllProducts(@QueryParam("categoryId") String categoryId,
                                  @QueryParam("search") String search) {
        List<Product> products;
        if (categoryId != null && !categoryId.trim().isEmpty()) {
            products = productService.getProductsByCategory(categoryId.trim());
        } else if (search != null && !search.trim().isEmpty()) {
            products = productService.searchProducts(search.trim());
        } else {
            products = productService.getAllActiveProducts();
        }
        return Response.ok(products).build();
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") String id) {
        return productService.getProductById(id)
                .map(p -> Response.ok(p).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createProduct(Product product) {
        Product created = productService.createProduct(product);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") String id, Product product) {
        Product updated = productService.updateProduct(id, product);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        productService.deleteProduct(id);
        return Response.noContent().build();
    }
}
