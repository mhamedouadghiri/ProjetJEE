package com.mhamed.ProjetJEE.services;

import com.mhamed.ProjetJEE.data.ApplicationDAO;
import com.mhamed.ProjetJEE.data.ApplicationDatasource;
import com.mhamed.ProjetJEE.model.Application;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/application/")
public class ApplicationService {

    private static final ApplicationDAO applicationDatasource = new ApplicationDatasource();

    @GET
    @Path("/{offer-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplications(@PathParam("offer-id") Long offerId) {
        List<Application> applications = applicationDatasource.getApplicationsByOfferId(offerId);
        if (applications == null || applications.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(applications).build();
    }

    @GET
    @Path("/{offer-id}/{student-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentApplicationToOffer(@PathParam("offer-id") Long offerId,
                                                 @PathParam("student-id") Long studentId) {
        Application application = applicationDatasource.getApplicationByOfferIdAndStudentId(offerId, studentId);
        if (application != null) {
            return Response.ok().entity(application).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
