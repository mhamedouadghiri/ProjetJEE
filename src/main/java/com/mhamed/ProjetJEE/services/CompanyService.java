package com.mhamed.ProjetJEE.services;

import com.mhamed.ProjetJEE.data.CompanyDAO;
import com.mhamed.ProjetJEE.data.CompanyDatasource;
import com.mhamed.ProjetJEE.data.InternshipOfferDAO;
import com.mhamed.ProjetJEE.data.InternshipOfferDatasource;
import com.mhamed.ProjetJEE.model.Company;
import com.mhamed.ProjetJEE.model.InternshipOffer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/company/")
public class CompanyService {

    private static final CompanyDAO companyDatasource = new CompanyDatasource();
    private static final InternshipOfferDAO offerDatasource = new InternshipOfferDatasource();

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

    @GET
    @Path("/offers/{company-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOffers(@PathParam("company-id") Long companyId) {
        List<InternshipOffer> offers = offerDatasource.getOffersByCompanyId(companyId);
        if (offers == null || offers.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(offers).build();
    }
}
