package com.example.userapi.service;

import com.example.userapi.model.UserRole;
import com.example.userapi.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserRole createRole(UserRole newRole) {
        return userRoleRepository.save(newRole);
    }

    @Override
    public List<UserRole> listRoles() {
        return (List<UserRole>) userRoleRepository.findAll();
    }

    @Override
    public UserRole getRole(String roleName) {
        return userRoleRepository.findByName(roleName);
    }
}
