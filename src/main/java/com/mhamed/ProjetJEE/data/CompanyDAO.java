package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Company;

public interface CompanyDAO extends UserDAO {

    Long save(Company entity);
}
