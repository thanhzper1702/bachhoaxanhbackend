package com.bachhoaxanh.staff;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST API Controller skeleton for Staff resources with VARCHAR(50) PK.
 */
@Path("/staff")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GET
    public Response getAllStaff() {
        List<Staff> staffList = staffService.getAllActiveStaff();
        return Response.ok(staffList).build();
    }

    @GET
    @Path("/{id}")
    public Response getStaffById(@PathParam("id") String id) {
        return staffService.getStaffById(id)
                .map(s -> Response.ok(s).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createStaff(Staff staff) {
        Staff created = staffService.createStaff(staff);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateStaff(@PathParam("id") String id, Staff staff) {
        Staff updated = staffService.updateStaff(id, staff);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStaff(@PathParam("id") String id) {
        staffService.deactivateStaff(id);
        return Response.noContent().build();
    }
}
