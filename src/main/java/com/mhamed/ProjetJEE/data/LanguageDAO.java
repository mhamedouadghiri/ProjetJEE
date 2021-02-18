package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Language;

import java.util.List;

public interface LanguageDAO extends BaseDAO<Language> {

    List<Language> getLanguagesByStudentId(Long studentId);
}
