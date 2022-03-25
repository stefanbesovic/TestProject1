package com.practice.test1.web.dto.playlistvideo;

import com.practice.test1.entities.PlaylistVideo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlaylistVideoMapper {
    PlaylistVideoMapper INSTANCE = Mappers.getMapper(PlaylistVideoMapper.class);

    PlaylistVideoGetDto toDto(PlaylistVideo playlistVideo);
    PlaylistVideo fromDto(PlaylistVideoGetDto playlistVideoDto);
}
