package com.auth.client;

import com.auth.api.AdminOperation;
import com.auth.api.UserOperation;
import com.auth.resources.User;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RolesAllowed({"ADMIN"})
@Path("/admin")
public class AdminAPI {

    @GET
    @Path("/getDetails")
    @Produces(MediaType.APPLICATION_JSON)
    public User getDetails(@QueryParam("id") String id){
        try {
            return UserOperation.getInstance().getDetails(id);
        } catch (Exception e) {
            return null;
        }
    }

    @POST
    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user){
        try {
            AdminOperation.getInstance().addUser(user);

            return Response.status(201).entity("User created").build();
        }catch (Exception e){
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/deleteUser")
    public Response deleteUser(@QueryParam("id") String id){
        try {
            AdminOperation.getInstance().deleteUser(id);

            return Response.status(200).entity("User with id " + id + " deleted").build();
        }catch (Exception e){
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll(){
        return AdminOperation.getInstance().getAll();
    }
}
