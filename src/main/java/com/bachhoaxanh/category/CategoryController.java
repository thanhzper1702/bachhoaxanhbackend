package com.bachhoaxanh.category;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST API Controller skeleton for Category resources with VARCHAR(50) PK.
 */
@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GET
    public Response getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return Response.ok(categories).build();
    }

    @GET
    @Path("/{id}")
    public Response getCategoryById(@PathParam("id") String id) {
        return categoryService.getCategoryById(id)
                .map(c -> Response.ok(c).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createCategory(Category category) {
        Category created = categoryService.createCategory(category);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCategory(@PathParam("id") String id, Category category) {
        Category updated = categoryService.updateCategory(id, category);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCategory(@PathParam("id") String id) {
        categoryService.deleteCategory(id);
        return Response.noContent().build();
    }
}
