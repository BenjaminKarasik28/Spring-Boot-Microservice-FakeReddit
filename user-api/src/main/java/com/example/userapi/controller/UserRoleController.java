package com.example.userapi.controller;

import com.example.userapi.model.User;
import com.example.userapi.model.UserRole;
import com.example.userapi.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;

//    @GetMapping("/{rolename}")
//    public UserRole getRole(@PathVariable String rolename) {
//        return roleService.getRole(rolename);
//    }
//
//    @PostMapping
//    public UserRole createRole(@RequestBody UserRole role) {
//        return roleService.createRole(role);
//    }

    @PostMapping
    public UserRole createRole(@RequestBody UserRole userRole){
        return userRoleService.createRole(userRole);
    }

    @GetMapping("/list")
    public List<UserRole> listRoles(){
        return userRoleService.listRoles();
    }


}