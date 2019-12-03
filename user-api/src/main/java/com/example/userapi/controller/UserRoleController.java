package com.example.userapi.controller;

import com.example.userapi.model.User;
import com.example.userapi.model.UserRole;
import com.example.userapi.service.UserRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;

    @ApiOperation(value = "Create role", notes = "Provide role name", response = UserRole.class)
    @PostMapping
    public UserRole createRole(@RequestBody UserRole userRole){
        return userRoleService.createRole(userRole);
    }

    @ApiOperation(value = "Get roles", notes = "Get list of roles", response = UserRole.class, responseContainer = "List")
    @GetMapping("/list")
    public List<UserRole> listRoles(){
        return userRoleService.listRoles();
    }


}