package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Education;

import java.util.List;

public interface EducationDAO extends BaseDAO<Education> {

    List<Education> getEducationsByStudentId(Long studentId);
}
