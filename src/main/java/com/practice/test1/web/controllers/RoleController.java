package com.practice.test1.web.controllers;

import com.practice.test1.entities.RolePrivilege;
import com.practice.test1.entities.UserRole;
import com.practice.test1.web.dto.privilege.RolePrivilegeRequest;
import com.practice.test1.web.dto.role.UserRoleDto;
import com.practice.test1.web.dto.role.UserRoleMapper;
import com.practice.test1.services.UserRoleService;
import com.practice.test1.web.dto.privilege.RolePrivilegeDto;
import com.practice.test1.web.dto.privilege.RolePrivilegeMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
@Tag(name = "Role Controller", description = "Set of endpoints for Creating of Role as well as Creating of Privileges and Adding them to Roles")
public class RoleController {

    private final UserRoleService userRoleService;

    @Operation(summary = "Creates new User Role")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User Role"),
            @ApiResponse(responseCode = "400", description = "Validation error : invalid argument"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/role")
    public UserRoleDto saveRole(@Valid @RequestBody UserRoleDto roleDto) {
        UserRole userRole = userRoleService.saveRole(UserRoleMapper.INSTANCE.fromDto(roleDto));
        return UserRoleMapper.INSTANCE.toDto(userRole);
    }

    @Operation(summary = "Creates new Role Privilege")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Role Privilege"),
            @ApiResponse(responseCode = "400", description = "Validation error : invalid argument"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/privilege")
    public RolePrivilegeDto savePrivilege(@Valid @RequestBody RolePrivilegeDto rolePrivilegeDto) {
        RolePrivilege rolePrivilege = userRoleService.savePrivilege(RolePrivilegeMapper.INSTANCE.fromDto(rolePrivilegeDto));
        return RolePrivilegeMapper.INSTANCE.toDto(rolePrivilege);
    }

    @Operation(summary = "Add Privilege to Role")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User Role"),
            @ApiResponse(responseCode = "404", description = "Privilege or Role not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/role/add-privilege")
    public UserRoleDto addPrivilegeToRole(@Valid @RequestBody RolePrivilegeRequest request) {
        UserRole userRole = userRoleService.addPrivilegeToRole(request.getUserRoleName(), request.getRolePrivilegeName());
        return UserRoleMapper.INSTANCE.toDto(userRole);
    }
}
