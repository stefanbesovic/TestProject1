package com.practice.test1.web.dto.role;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class RoleUserGet {

    @NotEmpty
    @Size(min = 4, max = 8, message = "Username should be between 4 and 8 characters.")
    private String username;

    @NotEmpty
    @Size(min = 4, max = 10, message = "User role name should be between 4 and 10 characters.")
    private String name;
}
