package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Skill;

import java.util.List;

public interface SkillDAO extends BaseDAO<Skill> {

    List<Skill> getSkillsByStudentId(Long studentId);
}
