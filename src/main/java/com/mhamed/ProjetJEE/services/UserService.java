package com.mhamed.ProjetJEE.services;

import com.mhamed.ProjetJEE.data.*;
import com.mhamed.ProjetJEE.model.User;
import com.mhamed.ProjetJEE.model.UserType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users/")
public class UserService {

    private static final InternshipsManagerDAO internshipsManagerDatasource = new InternshipsManagerDatasource();
    private static final CompanyDAO companyDatasource = new CompanyDatasource();
    private static final SchoolDAO schoolDatasource = new SchoolDatasource();
    private static final RepresentativeDAO representativeDatasource = new RepresentativeDatasource();
    private static final StudentDAO studentDatasource = new StudentDatasource();

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") Long id, @QueryParam("user-type") UserType userType) {
        User user;
        switch (userType) {
            case internships_manager:
                user = internshipsManagerDatasource.get(id);
                break;
            case company:
                user = companyDatasource.get(id);
                break;
            case school:
                user = schoolDatasource.get(id);
                break;
            case representative:
                user = representativeDatasource.get(id);
                break;
            case student:
                user = studentDatasource.get(id);
                break;
            default:
                return Response.status(Response.Status.FORBIDDEN).build();
        }
        if (user == null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } else {
            return Response.ok().entity(user).build();
        }
    }
}
