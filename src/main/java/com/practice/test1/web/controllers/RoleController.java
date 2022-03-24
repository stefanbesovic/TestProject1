package com.practice.test1.web.controllers;

import com.practice.test1.entities.RolePrivilege;
import com.practice.test1.entities.UserRole;
import com.practice.test1.web.dto.privilege.RolePrivilegeRequest;
import com.practice.test1.web.dto.role.UserRoleDto;
import com.practice.test1.web.dto.role.UserRoleMapper;
import com.practice.test1.services.UserRoleService;
import com.practice.test1.web.dto.privilege.RolePrivilegeDto;
import com.practice.test1.web.dto.privilege.RolePrivilegeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class RoleController {

    private final UserRoleService userRoleService;

    @PostMapping("/role")
    public UserRoleDto saveRole(@Valid @RequestBody UserRoleDto roleDto) {
        UserRole userRole = userRoleService.saveRole(UserRoleMapper.INSTANCE.fromDto(roleDto));
        return UserRoleMapper.INSTANCE.toDto(userRole);
    }

    @PostMapping("/privilege")
    public RolePrivilegeDto savePrivilege(@Valid @RequestBody RolePrivilegeDto rolePrivilegeDto) {
        RolePrivilege rolePrivilege = userRoleService.savePrivilege(RolePrivilegeMapper.INSTANCE.fromDto(rolePrivilegeDto));
        return RolePrivilegeMapper.INSTANCE.toDto(rolePrivilege);
    }

    @PostMapping("/role/add-privilege")
    public UserRoleDto addPrivilegeToRole(@Valid @RequestBody RolePrivilegeRequest request) {
        UserRole userRole = userRoleService.addPrivilegeToRole(request.getUserRoleName(), request.getRolePrivilegeName());
        return UserRoleMapper.INSTANCE.toDto(userRole);
    }
}
