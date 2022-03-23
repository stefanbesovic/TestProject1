package com.practice.test1.web.dto.user;

import com.practice.test1.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserGetMapper {
    UserGetMapper INSTANCE = Mappers.getMapper(UserGetMapper.class);

    UserGetDto toDto(User user);
    User fromDto(UserGetDto userGetDto);
}
