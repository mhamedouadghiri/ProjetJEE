package com.mhamed.ProjetJEE.services;

import com.mhamed.ProjetJEE.data.*;
import com.mhamed.ProjetJEE.model.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Path("/student/")
public class StudentService {

    private static final EducationDAO educationDatasource = new EducationDatasource();
    private static final ExperienceDAO experienceDatasource = new ExperienceDatasource();
    private static final LanguageDAO languageDatasource = new LanguageDatasource();
    private static final SkillDAO skillDatasource = new SkillDatasource();
    private static final ApplicationDAO applicationDatasource = new ApplicationDatasource();

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
    public Response saveEducation(@FormParam("start-date") String start,
                                  @FormParam("end-date") String end,
                                  @FormParam("name") String name,
                                  @FormParam("level") String level,
                                  @FormParam("student-id") Long studentId) {
        if (name == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        LocalDate startDate = null;
        LocalDate endDate = null;
        try {
            startDate = new Date(start).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception ignored) {
        }
        try {
            endDate = new Date(end).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception ignored) {
        }
        Education education = new Education(null, startDate, endDate, name, level, studentId);
        Long savedId = educationDatasource.save(education);
        if (savedId != null && savedId > 0L) {
            education.setId(savedId);
            return Response.ok().entity(education).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("/save/experience")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response saveExperience(@FormParam("start-date") String start,
                                   @FormParam("end-date") String end,
                                   @FormParam("description") String description,
                                   @FormParam("student-id") Long studentId) {
        if (description == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        LocalDate startDate = null;
        LocalDate endDate = null;
        try {
            startDate = new Date(start).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception ignored) {
        }
        try {
            endDate = new Date(end).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception ignored) {
        }
        Experience experience = new Experience(null, startDate, endDate, description, studentId);
        Long savedId = experienceDatasource.save(experience);
        if (savedId != null && savedId > 0L) {
            experience.setId(savedId);
            return Response.ok().entity(experience).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("/save/language")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response saveLanguage(@FormParam("name") String name,
                                 @FormParam("level") String level,
                                 @FormParam("student-id") Long studentId) {
        if (name == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Language language = new Language(null, name, level, studentId);
        Long savedId = languageDatasource.save(language);
        if (savedId != null && savedId > 0L) {
            language.setId(savedId);
            return Response.ok().entity(language).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("/save/skill")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response saveSkill(@FormParam("name") String name,
                              @FormParam("level") String level,
                              @FormParam("student-id") Long studentId) {
        if (name == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Skill skill = new Skill(null, name, level, studentId);
        Long savedId = skillDatasource.save(skill);
        if (savedId != null && savedId > 0L) {
            skill.setId(savedId);
            return Response.ok().entity(skill).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("/apply")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response apply(@FormParam("student-id") Long studentId,
                          @FormParam("offer-id") Long offerId,
                          @FormParam("cover-letter") String coverLetter) {
        Application application = new Application(offerId, studentId, coverLetter);
        Long savedId = applicationDatasource.save(application);
        if (savedId != null && savedId == 951753852456L) { // check Application DAO implementation
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
