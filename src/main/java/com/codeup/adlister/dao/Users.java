package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.codeup.adlister.models.Validation;

public interface Users {
    User findByUsername(String username);
    User findById(Long id);

    Long insert(User user);
    boolean update(User user);



    boolean userExistsByUsername(String username);


    Validation editUser(Long id, String username, String email, String password, String pwConfirm);
    Validation addNewUser(String username, String email, String password, String pwConfirm);

}
