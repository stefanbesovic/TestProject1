package com.practice.test1.web.dto.playlist;

import com.practice.test1.entities.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface PlaylistMapper {
    PlaylistMapper INSTANCE = Mappers.getMapper(PlaylistMapper.class);

    PlaylistDto toDto(Playlist playlist);
    Playlist fromDto(PlaylistDto playlistDto);
}
