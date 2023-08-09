package com.registration.registration.services;


import com.registration.registration.DTO.UserRequest;
import com.registration.registration.models.User;
import com.registration.registration.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User createStaff(UserRequest userRequest, String role) {
        var staffUser = (role.equals("ADMIN")? userRequest.createAdmin(userRequest): userRequest.createEmployee(userRequest));
        return userRepository.save(staffUser);
    }

    @Override
    public User createExternal(UserRequest userRequest) {

        return userRepository.save(userRequest.createClient(userRequest));

    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        var user = userRepository.findUserByEmail(email);
        return user;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Collection<User> getUsersByDepartmentId(String dept_id) {
        return userRepository.findUserByDepartment(dept_id);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }


}
