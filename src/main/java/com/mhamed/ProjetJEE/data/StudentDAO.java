package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Student;

public interface StudentDAO extends UserDAO {

    Long save(Student entity);
}
