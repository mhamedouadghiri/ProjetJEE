package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Student;

public class StudentDatasource extends BaseDatasource<Student> implements StudentDAO {

    @Override
    public Student get(Long id) {
        return null;
    }

    @Override
    public Long save(Student entity) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
