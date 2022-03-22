package com.practice.test1.web.dto.video;

import com.practice.test1.entities.Video;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VideoGetMapper {
    VideoGetMapper INSTANCE = Mappers.getMapper(VideoGetMapper.class);

    VideoGetDto toDto(Video video);

    Video fromDto(VideoGetDto videoGetDto);
}
