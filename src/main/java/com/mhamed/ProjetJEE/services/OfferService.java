package com.mhamed.ProjetJEE.services;

import com.mhamed.ProjetJEE.data.InternshipOfferDAO;
import com.mhamed.ProjetJEE.data.InternshipOfferDatasource;
import com.mhamed.ProjetJEE.model.InternshipOffer;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Path("/offer/")
public class OfferService {

    public static final InternshipOfferDAO offerDatasource = new InternshipOfferDatasource();

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response saveOffer(MultivaluedMap<String, String> formParams) {
        String title = formParams.getFirst("title");
        Long companyId = null;
        try {
            companyId = Long.parseLong(formParams.getFirst("company-id"));
        } catch (NumberFormatException ignored) {
        }
        String statusForm = formParams.getFirst("status");
        boolean status = statusForm != null && !statusForm.equals("false");
        if (title == null || companyId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Integer duration = null;
        try {
            duration = Integer.parseInt(formParams.getFirst("duration"));
        } catch (NumberFormatException ignored) {
        }
        Integer pay = null;
        try {
            pay = Integer.parseInt(formParams.getFirst("pay"));
        } catch (NumberFormatException ignored) {
        }
        String description = formParams.getFirst("description");
        String field = formParams.getFirst("field");
        LocalDate startDate = null;
        try {
            startDate = new Date(formParams.getFirst("start-date")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception ignored) {
        }
        LocalDate endDate = null;
        try {
            endDate = new Date(formParams.getFirst("end-date")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception ignored) {
        }

        InternshipOffer entity = new InternshipOffer(
                null, title, duration, startDate, endDate, description, pay, status, field, companyId);

        Long savedId = offerDatasource.save(entity);
        if (savedId != null) {
            entity.setId(savedId);
            return Response.ok().entity(entity).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
