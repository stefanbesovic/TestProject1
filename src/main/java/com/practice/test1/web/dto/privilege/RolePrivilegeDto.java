package com.practice.test1.web.dto.privilege;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Schema(description = "RolePrivilege response")
@Data
public class RolePrivilegeDto {

    @Schema(description = "Privilege name")
    @NotEmpty
    @Size(min = 4, max = 15, message = "Role privilege name should be between 4 and 15 characters.")
    private String name;
}
