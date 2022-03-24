package com.practice.test1.services.implementation;

import com.practice.test1.entities.RolePrivilege;
import com.practice.test1.entities.UserRole;
import com.practice.test1.repositories.RolePrivilegeRepository;
import com.practice.test1.repositories.UserRoleRepository;
import com.practice.test1.services.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserRoleServiceImplementation implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final RolePrivilegeRepository rolePrivilegeRepository;

    @Override
    public UserRole saveRole(UserRole role) {
        log.info("Saving role: id={} & name={}", role.getId(), role.getName());
        return userRoleRepository.save(role);
    }

    @Override
    public RolePrivilege savePrivilege(RolePrivilege rolePrivilege) {
        return rolePrivilegeRepository.save(rolePrivilege);
    }

    @Override
    public UserRole findRoleByName(String roleName) {
        return userRoleRepository.findByName(roleName).orElseThrow( () ->
                new NoSuchElementException(String.format("Role with name %s does not exist.", roleName))
        );
    }

    @Override
    public RolePrivilege findPrivilegeByName(String privilegeName) {
        return rolePrivilegeRepository.findByName(privilegeName).orElseThrow( () ->
                new NoSuchElementException(String.format("Privilege with name %s does not exist.", privilegeName))
        );
    }

    @Override
    public UserRole addPrivilegeToRole(String userRoleName, String rolePrivilegeName) {
        log.info("Adding privilege {} to role {}", rolePrivilegeName, userRoleName);

        UserRole userRole = findRoleByName(userRoleName);
        RolePrivilege rolePrivilege = findPrivilegeByName(rolePrivilegeName);

        userRole.getPrivileges().add(rolePrivilege);

        return userRoleRepository.save(userRole);
    }
}
