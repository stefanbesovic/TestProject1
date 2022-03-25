package com.practice.test1.web.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "User short response")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGetDto {

    @Schema(description = "User id")
    private Long id;

    @Schema(description = "User username")
    private String username;
}
