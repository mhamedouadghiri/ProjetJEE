package com.mhamed.ProjetJEE.services;

import com.mhamed.ProjetJEE.data.*;
import com.mhamed.ProjetJEE.model.Education;
import com.mhamed.ProjetJEE.model.Experience;
import com.mhamed.ProjetJEE.model.Language;
import com.mhamed.ProjetJEE.model.Skill;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
}
