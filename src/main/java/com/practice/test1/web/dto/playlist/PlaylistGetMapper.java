package com.practice.test1.web.dto.playlist;

import com.practice.test1.entities.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlaylistGetMapper {
    PlaylistGetMapper INSTANCE = Mappers.getMapper(PlaylistGetMapper.class);

    PlaylistGetDto toDto(Playlist playlist);

    Playlist fromDto(PlaylistGetDto playlistGetDto);
}
