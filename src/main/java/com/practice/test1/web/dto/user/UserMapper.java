package com.practice.test1.web.dto.user;

import com.practice.test1.entities.User;
import com.practice.test1.web.dto.playlist.PlaylistMapper;
import com.practice.test1.web.dto.role.UserRoleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserRoleMapper.class, PlaylistMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "user.userRoles", target = "userRolesDto")
    @Mapping(source = "user.playlists", target = "playlistsDto")
    UserDto toDto(User user);

    User fromDto(UserDto userDto);
}
