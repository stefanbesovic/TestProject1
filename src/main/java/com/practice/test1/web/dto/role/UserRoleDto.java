package com.practice.test1.web.dto.role;

import com.practice.test1.web.dto.privilege.RolePrivilegeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleDto {

    private long id;

    @NotEmpty
    @Size(min = 4, max = 15, message = "Role name should be between 4 and 15 characters.")
    private String name;

    private Set<RolePrivilegeDto> privileges;
}
