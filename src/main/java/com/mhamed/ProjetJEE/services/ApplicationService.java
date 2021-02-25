package com.mhamed.ProjetJEE.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mhamed.ProjetJEE.data.ApplicationDAO;
import com.mhamed.ProjetJEE.data.ApplicationDatasource;
import com.mhamed.ProjetJEE.model.Application;
import com.mhamed.ProjetJEE.util.StudentUtils;

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
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode allApplications = mapper.createArrayNode();
        for (Application application : applications) {
            try {
                JsonNode applicationNode = mapper.convertValue(application, JsonNode.class);
                ObjectNode jsonNode = applicationNode.deepCopy();

                ObjectNode studentInfo = StudentUtils.studentInfoAsObjectNode(application.getStudentId());
                jsonNode.set("studentInfo", studentInfo);

                allApplications.add(jsonNode);
            } catch (Exception ignored) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
        return Response.ok().entity(allApplications.toString()).build();
    }

    @GET
    @Path("/{offer-id}/{student-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplicationAndStudentInfoRegardingOffer(@PathParam("offer-id") Long offerId,
                                                               @PathParam("student-id") Long studentId) {
        Application application = applicationDatasource.getApplicationByOfferIdAndStudentId(offerId, studentId);
        if (application != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();

                JsonNode applicationNode = mapper.convertValue(application, JsonNode.class);
                ObjectNode jsonNode = applicationNode.deepCopy();

                ObjectNode studentInfo = StudentUtils.studentInfoAsObjectNode(studentId);
                jsonNode.set("studentInfo", studentInfo);

                String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

                return Response.ok().entity(json).build();
            } catch (Exception ignored) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
