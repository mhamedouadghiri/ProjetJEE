package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Student;

import java.util.List;

public interface StudentDAO extends UserDAO {

    Long save(Student entity);

    List<Student> getStudentsBySchoolId(Long schoolId);
}
