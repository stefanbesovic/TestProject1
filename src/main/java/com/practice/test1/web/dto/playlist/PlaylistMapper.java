package com.practice.test1.web.dto.playlist;

import com.practice.test1.entities.Playlist;
import com.practice.test1.web.dto.category.CategoryMapper;
import com.practice.test1.web.dto.playlistvideo.PlaylistVideoMapper;
import com.practice.test1.web.dto.video.VideoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PlaylistVideoMapper.class, CategoryMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlaylistMapper {
    PlaylistMapper INSTANCE = Mappers.getMapper(PlaylistMapper.class);

    @Mapping(source = "playlist.videos", target = "videosDto")
    @Mapping(source = "playlist.categories", target = "categoriesDto")
    PlaylistDto toDto(Playlist playlist);

    Playlist fromDto(PlaylistDto playlistDto);
}
