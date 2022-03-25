package com.practice.test1.web.dto.user;

import com.practice.test1.web.dto.playlist.PlaylistGetDto;
import com.practice.test1.web.dto.role.UserRoleDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Schema(description = "User response")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @Schema(description = "User id")
    private Long Id;

    @Schema(description = "User name")
    @Size(min = 3, max = 20, message = "Name of user should be between 3 and 20 characters.")
    private String name;

    @Schema(description = "User username")
    @Size(min = 4, max = 8, message = "Username should be between 4 and 8 characters.")
    private String username;

    @Schema(description = "User roles")
    private Set<UserRoleDto> userRoles;

    @Schema(description = "User playlists")
    private Set<PlaylistGetDto> playlists;
}
