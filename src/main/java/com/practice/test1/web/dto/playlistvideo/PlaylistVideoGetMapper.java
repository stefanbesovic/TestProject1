package com.practice.test1.web.dto.playlistvideo;

import com.practice.test1.entities.PlaylistVideo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlaylistVideoGetMapper {
    PlaylistVideoGetMapper INSTANCE = Mappers.getMapper(PlaylistVideoGetMapper.class);

    PlaylistVideoGetDto toDto(PlaylistVideo playlistVideo);
    PlaylistVideo fromDto(PlaylistVideoGetDto playlistVideoDto);
}
