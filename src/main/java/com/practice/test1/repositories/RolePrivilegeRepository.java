package com.practice.test1.repositories;

import com.practice.test1.entities.RolePrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolePrivilegeRepository extends JpaRepository<RolePrivilege, Long> {
    Optional<RolePrivilege> findByName(String name);
}
