package com.practice.test1.web.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Schema(description = "User request")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDto {

    @Schema(description = "User name")
    @NotEmpty
    @Size(min = 3, max = 20, message = "Name of user should be between 3 and 20 characters.")
    private String name;

    @Schema(description = "User username")
    @NotEmpty
    @Size(min = 4, max = 8, message = "Username should be between 4 and 8 characters.")
    private String username;

    @Schema(description = "User password")
    @NotEmpty
    @Size(min = 4, max = 12, message = "Password should be between 4 and 12 characters.")
    private String password;
}
