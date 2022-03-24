package com.practice.test1.web.dto.privilege;

import com.practice.test1.entities.RolePrivilege;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RolePrivilegeMapper {
    RolePrivilegeMapper INSTANCE = Mappers.getMapper(RolePrivilegeMapper.class);

    RolePrivilegeDto toDto(RolePrivilege rolePrivilege);
    RolePrivilege fromDto(RolePrivilegeDto rolePrivilegeDto);
}
