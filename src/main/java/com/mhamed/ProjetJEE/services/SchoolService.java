package com.mhamed.ProjetJEE.services;

import com.mhamed.ProjetJEE.data.StudentDAO;
import com.mhamed.ProjetJEE.data.StudentDatasource;
import com.mhamed.ProjetJEE.model.Student;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/school/")
public class SchoolService {

    private static final StudentDAO studentDatasource = new StudentDatasource();

    @GET
    @Path("/students/{school-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents(@PathParam("school-id") Long schoolId) {
        List<Student> students = studentDatasource.getStudentsBySchoolId(schoolId);
        if (students == null || students.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(students).build();
    }
}
