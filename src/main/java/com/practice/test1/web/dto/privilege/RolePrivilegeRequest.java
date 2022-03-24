package com.practice.test1.web.dto.privilege;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class RolePrivilegeRequest {

    @NotEmpty
    @Size(min = 4, max = 15, message = "Role name should be between 4 and 15 characters.")
    String userRoleName;

    @NotEmpty
    @Size(min = 4, max = 15, message = "Role privilege name should be between 4 and 15 characters.")
    String rolePrivilegeName;
}
