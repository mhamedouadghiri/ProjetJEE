package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.User;

public interface UserDAO {

    User get(Long id);

    Boolean delete(Long id);
}
