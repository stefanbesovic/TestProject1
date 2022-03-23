package com.practice.test1.web.controllers;

import com.practice.test1.entities.UserRole;
import com.practice.test1.services.UserService;
import com.practice.test1.web.dto.role.UserRoleDto;
import com.practice.test1.web.dto.role.UserRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/role")
public class RoleController {

    private final UserService userService;

    @PostMapping()
    public UserRoleDto saveRole(@Valid @RequestBody UserRoleDto roleDto) {
        UserRole userRole = userService.saveRole(UserRoleMapper.INSTANCE.fromDto(roleDto));
        return UserRoleMapper.INSTANCE.toDto(userRole);
    }
}
