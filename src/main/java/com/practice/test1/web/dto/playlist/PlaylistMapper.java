package com.practice.test1.web.dto.playlist;

import com.practice.test1.entities.Playlist;
import com.practice.test1.web.dto.playlistvideo.PlaylistVideoMapper;
import com.practice.test1.web.dto.category.CategoryMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PlaylistVideoMapper.class, CategoryMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlaylistMapper {
    PlaylistMapper INSTANCE = Mappers.getMapper(PlaylistMapper.class);

    PlaylistDto toDto(Playlist playlist);
    Playlist fromDto(PlaylistDto playlistDto);

    PlaylistGetDto toGetDto(Playlist playlist);
    Playlist fromGetDto(PlaylistGetDto playlistGetDto);
}
