package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.School;

public interface SchoolDAO extends UserDAO {

    Long save(School entity);
}
