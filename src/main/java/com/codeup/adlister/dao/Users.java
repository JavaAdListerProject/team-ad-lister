package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.codeup.adlister.models.Validation;

public interface Users {
    User findByUsername(String username);
    Long insert(User user);

    boolean userExistsByUsername(String username);
    Validation addNewUser(String username, String email, String password, String pwConfirm);

}
