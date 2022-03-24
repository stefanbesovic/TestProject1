package com.practice.test1.web.dto.user;

import com.practice.test1.web.dto.playlist.PlaylistGetDto;
import com.practice.test1.web.dto.role.UserRoleDto;
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
public class UserDto {

    private long id;

    @NotEmpty
    @Size(min = 3, max = 20, message = "Name of user should be between 3 and 20 characters.")
    private String name;

    @NotEmpty
    @Size(min = 4, max = 8, message = "Username should be between 4 and 8 characters.")
    private String username;

    private Set<UserRoleDto> userRoles;
    private Set<PlaylistGetDto> playlists;
}
