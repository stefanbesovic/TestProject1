package com.practice.test1.web.dto.category;

import com.practice.test1.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryGetMapper {
    CategoryGetMapper INSTANCE = Mappers.getMapper(CategoryGetMapper.class);

    CategoryGetDto toDto(Category category);
    Category fromDto(CategoryGetDto categoryGetDto);
}
