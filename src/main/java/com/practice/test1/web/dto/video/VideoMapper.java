package com.practice.test1.web.dto.video;

import com.practice.test1.entities.Video;
import com.practice.test1.web.dto.category.CategoryMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VideoMapper {
    VideoMapper INSTANCE = Mappers.getMapper(VideoMapper.class);

    VideoDto toDto(Video video);
    Video fromDto(VideoDto videoDto);
}
