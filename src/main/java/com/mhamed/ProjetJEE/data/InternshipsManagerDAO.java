package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.InternshipsManager;

public interface InternshipsManagerDAO extends UserDAO {

    Long save(InternshipsManager entity);
}
