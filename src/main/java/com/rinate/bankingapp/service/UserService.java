package com.rinate.bankingapp.service;

import com.rinate.bankingapp.domain.model.User;
import com.rinate.bankingapp.domain.model.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);

    User createUser(User user, Set<UserRole> userRoles);

    void enableUser (String username);

    void disableUser (String username);

    User findByUsername(String username);

    User saveUser (User user);

}
