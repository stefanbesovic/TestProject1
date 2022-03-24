package com.practice.test1.services;

import com.practice.test1.entities.RolePrivilege;
import com.practice.test1.entities.UserRole;

public interface UserRoleService {

    UserRole saveRole(UserRole role);
    RolePrivilege savePrivilege(RolePrivilege userPrivilege);
    UserRole addPrivilegeToRole(String userRoleName, String rolePrivilegeName);
    UserRole findRoleByName(String roleName);
    RolePrivilege findPrivilegeByName(String privilegeName);
}
