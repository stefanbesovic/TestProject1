package com.practice.test1.web.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Schema(description = "UserRole request")
@Data
public class RoleUserGet {

    @Schema(description = "User username")
    @NotEmpty
    @Size(min = 4, max = 8, message = "Username should be between 4 and 8 characters.")
    private String username;

    @Schema(description = "Role name")
    @NotEmpty
    @Size(min = 4, max = 10, message = "User role name should be between 4 and 10 characters.")
    private String name;
}
