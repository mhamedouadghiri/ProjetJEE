package com.mhamed.ProjetJEE.services;

import com.mhamed.ProjetJEE.data.*;
import com.mhamed.ProjetJEE.model.Education;
import com.mhamed.ProjetJEE.model.Experience;
import com.mhamed.ProjetJEE.model.Language;
import com.mhamed.ProjetJEE.model.Skill;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/student/")
public class StudentService {

    private static final EducationDAO educationDatasource = new EducationDatasource();
    private static final ExperienceDAO experienceDatasource = new ExperienceDatasource();
    private static final LanguageDAO languageDatasource = new LanguageDatasource();
    private static final SkillDAO skillDatasource = new SkillDatasource();

    @GET
    @Path("/educations/{student-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEducations(@PathParam("student-id") Long studentId) {
        List<Education> educations = educationDatasource.getEducationsByStudentId(studentId);
        if (educations == null || educations.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(educations).build();
    }

    @GET
    @Path("/experiences/{student-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExperiences(@PathParam("student-id") Long studentId) {
        List<Experience> experiences = experienceDatasource.getExperiencesByStudentId(studentId);
        if (experiences == null || experiences.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(experiences).build();
    }

    @GET
    @Path("/languages/{student-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLanguages(@PathParam("student-id") Long studentId) {
        List<Language> languageList = languageDatasource.getLanguagesByStudentId(studentId);
        if (languageList == null || languageList.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(languageList).build();
    }

    @GET
    @Path("/skills/{student-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSkills(@PathParam("student-id") Long studentId) {
        List<Skill> skills = skillDatasource.getSkillsByStudentId(studentId);
        if (skills == null || skills.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(skills).build();
    }

    @POST
    @Path("/save/education")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response saveEducation(@FormParam("start-date") String startDate,
                                  @FormParam("end-date") String endDate,
                                  @FormParam("name") String name,
                                  @FormParam("level") String level,
                                  @FormParam("student-id") Long studentId) {
        if (name == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        LocalDate start = null;
        LocalDate end = null;
        try {
            start = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
            end = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception ignored) {
        }
        Education education = new Education(null, start, end, name, level, studentId);
        Long savedId = educationDatasource.save(education);
        if (savedId != null && savedId > 0L) {
            education.setId(savedId);
            return Response.ok().entity(education).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
