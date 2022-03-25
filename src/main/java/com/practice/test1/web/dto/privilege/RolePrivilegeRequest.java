package com.practice.test1.web.dto.privilege;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Schema(description = "RolePrivilege request")
@Data
public class RolePrivilegeRequest {

    @Schema(description = "Role name")
    @NotEmpty
    @Size(min = 4, max = 15, message = "Role name should be between 4 and 15 characters.")
    String userRoleName;

    @Schema(description = "Privilege name")
    @NotEmpty
    @Size(min = 4, max = 15, message = "Role privilege name should be between 4 and 15 characters.")
    String rolePrivilegeName;
}
