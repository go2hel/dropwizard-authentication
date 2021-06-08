package com.auth.client;

import com.auth.api.UserOperation;
import com.auth.resources.User;
import io.dropwizard.auth.Auth;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserAPI {

    @PUT
    @Path("/setPassword")
    public Response setPassword(@Auth User user,@NotNull @QueryParam("password") String password){
        try {
            UserOperation.getInstance().setPassword(user.getId(),password);

            return Response.status(201).entity("Password changed").build();
        }catch (Exception e){
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getDetails")
    @Produces(MediaType.APPLICATION_JSON)
    public User getDetails(@Auth User user){
        try {
            return UserOperation.getInstance().getDetails(user.getId());
        }catch (Exception e){
            return null;
        }
    }
}
