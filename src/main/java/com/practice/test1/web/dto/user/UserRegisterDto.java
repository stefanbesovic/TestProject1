package com.practice.test1.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDto {

    @NotEmpty
    @Size(min = 3, max = 20, message = "Name of user should be between 3 and 20 characters.")
    private String name;

    @NotEmpty
    @Size(min = 4, max = 8, message = "Username should be between 4 and 8 characters.")
    private String username;

    @NotEmpty
    @Size(min = 4, max = 12, message = "Password should be between 4 and 12 characters.")
    private String password;
}
