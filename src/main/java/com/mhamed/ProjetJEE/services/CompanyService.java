package com.mhamed.ProjetJEE.services;

import com.mhamed.ProjetJEE.data.CompanyDAO;
import com.mhamed.ProjetJEE.data.CompanyDatasource;
import com.mhamed.ProjetJEE.model.Company;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/company/")
public class CompanyService {

    private static final CompanyDAO companyDatasource = new CompanyDatasource();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Company> companies = companyDatasource.getAll();
        if (companies == null || companies.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(companies).build();
    }
}
