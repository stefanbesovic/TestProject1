package com.practice.test1.web.dto.user;

import com.practice.test1.entities.UserRole;
import com.practice.test1.web.dto.playlist.PlaylistDto;
import com.practice.test1.web.dto.role.UserRoleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String name;
    private String username;
    private Set<UserRoleDto> userRolesDto;
    private Set<PlaylistDto> playlistsDto;
}
