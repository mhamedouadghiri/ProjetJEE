package com.mhamed.ProjetJEE.services;

import com.mhamed.ProjetJEE.data.*;
import com.mhamed.ProjetJEE.model.*;
import org.mindrot.jbcrypt.BCrypt;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@Path("/users/")
public class UserService {

    private static final CompanyDAO companyDatasource = new CompanyDatasource();
    private static final SchoolDAO schoolDatasource = new SchoolDatasource();
    private static final StudentDAO studentDatasource = new StudentDatasource();

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") Long id, @QueryParam("user-type") UserType userType) {
        User user;
        switch (userType) {
            case company:
                user = companyDatasource.get(id);
                break;
            case school:
                user = schoolDatasource.get(id);
                break;
            case student:
                user = studentDatasource.get(id);
                break;
            default:
                return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok().entity(user).build();
        }
    }

    @POST
    @Path("/auth/check-user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response checkUserCredentials(@FormParam("email") String email,
                                         @FormParam("password") String password,
                                         @FormParam("user-type") UserType userType) {
        User user;
        switch (userType) {
            case company:
                user = companyDatasource.getByEmail(email);
                break;
            case school:
                user = schoolDatasource.getByEmail(email);
                break;
            case student:
                user = studentDatasource.getByEmail(email);
                break;
            default:
                return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return Response.ok().entity(user).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/auth/register-user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response registerUser(MultivaluedMap<String, String> formParams) {
        UserType userType;
        try {
            userType = UserType.valueOf(formParams.getFirst("user-type"));
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (formParams.getFirst("password") == null || formParams.getFirst("password").isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        String password = BCrypt.hashpw(formParams.getFirst("password"), BCrypt.gensalt());

        String email = formParams.getFirst("email");
        if (email == null || email.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        String phone = formParams.getFirst("phone");
        String name = formParams.getFirst("name");
        String description = formParams.getFirst("description");
        String city = formParams.getFirst("city");
        String country = formParams.getFirst("country");
        String address = formParams.getFirst("address");
        String firstName = formParams.getFirst("first-name");
        String lastName = formParams.getFirst("last-name");
        String major = formParams.getFirst("major");

        Long savedId;
        User entity;
        switch (userType) {
            case company:
                if (name == null) {
                    return Response.status(Response.Status.BAD_REQUEST).build();
                }
                entity = new Company(null, email, password, phone, name, description, city, country, address);
                savedId = companyDatasource.save((Company) entity);
                break;
            case school:
                if (name == null) {
                    return Response.status(Response.Status.BAD_REQUEST).build();
                }
                entity = new School(null, email, password, phone, name);
                savedId = schoolDatasource.save((School) entity);
                break;
            case student:
                if (firstName == null || lastName == null) {
                    return Response.status(Response.Status.BAD_REQUEST).build();
                }
                Long schoolYear = null;
                try {
                    schoolYear = Long.parseLong(formParams.getFirst("school-year"));
                } catch (NumberFormatException ignored) {
                }
                long schoolId;
                try {
                    schoolId = Long.parseLong(formParams.getFirst("school-id"));
                } catch (NumberFormatException e) {
                    return Response.status(Response.Status.BAD_REQUEST).build();
                }
                entity = new Student(null, email, password, phone, firstName, lastName, city, country, address, false, schoolYear, major, schoolId);
                savedId = studentDatasource.save((Student) entity);
                break;
            default:
                return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (savedId != null) {
            entity.setId(savedId);
            return Response.ok().entity(entity).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
