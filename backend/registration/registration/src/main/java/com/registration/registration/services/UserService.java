package com.registration.registration.services;


import com.registration.registration.DTO.UserRequest;
import com.registration.registration.models.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    User createStaff(UserRequest userRequest, String role);

    User createExternal(UserRequest userRequest);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByUsername(String username);

    Collection<User> getUsersByDepartmentId(String dept_id);

    Collection<User> getAllUsers();
}
