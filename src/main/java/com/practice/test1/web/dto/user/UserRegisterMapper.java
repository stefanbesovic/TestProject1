package com.practice.test1.web.dto.user;

import com.practice.test1.entities.User;
import com.practice.test1.web.dto.playlist.PlaylistMapper;
import com.practice.test1.web.dto.role.UserRoleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRegisterMapper {
    UserRegisterMapper INSTANCE = Mappers.getMapper(UserRegisterMapper.class);

    UserRegisterDto toDto(User user);
    User fromDto(UserRegisterDto userDto);
}
