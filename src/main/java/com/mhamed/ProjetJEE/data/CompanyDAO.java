package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Company;

import java.util.List;

public interface CompanyDAO extends UserDAO {

    Long save(Company entity);

    List<Company> getAll();
}
