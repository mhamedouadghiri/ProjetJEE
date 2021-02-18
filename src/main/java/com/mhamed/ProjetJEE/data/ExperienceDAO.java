package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Experience;

import java.util.List;

public interface ExperienceDAO extends BaseDAO<Experience> {

    List<Experience> getExperiencesByStudentId(Long studentId);
}
