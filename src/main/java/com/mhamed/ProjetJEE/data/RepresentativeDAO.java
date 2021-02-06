package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Representative;

public interface RepresentativeDAO extends UserDAO {

    Long save(Representative entity);
}
