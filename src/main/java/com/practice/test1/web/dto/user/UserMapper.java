package com.practice.test1.web.dto.user;

import com.practice.test1.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);
    User fromDto(UserDto userDto);

    UserGetDto toGetDto(User user);
    User fromGetDto(UserGetDto userGetDto);

    UserRegisterDto toRegDto(User user);
    User fromRegDto(UserRegisterDto userDto);
}
