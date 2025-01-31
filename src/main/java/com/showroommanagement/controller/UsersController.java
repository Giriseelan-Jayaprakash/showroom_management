package com.showroommanagement.controller;

import com.showroommanagement.dto.ResponseDTO;
import com.showroommanagement.entity.Users;
import com.showroommanagement.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/create-user/demo")
    public ResponseDTO createUser(@RequestBody final Users users){
        return usersService.createUser(users);
    }
    @PostMapping("/login")
    public ResponseDTO verifyUser(@RequestBody final Users users){
        return usersService.verifyUser(users);
    }
}
