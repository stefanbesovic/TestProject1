package com.practice.test1.web.dto.playlist;

import com.practice.test1.entities.Playlist;
import com.practice.test1.web.dto.playlistvideo.PlaylistVideoGetMapper;
import com.practice.test1.web.dto.category.CategoryMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PlaylistVideoGetMapper.class, CategoryMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlaylistMapper {
    PlaylistMapper INSTANCE = Mappers.getMapper(PlaylistMapper.class);

//    @Mapping(source = "playlist.videos", target = "videosDto")
//    @Mapping(source = "playlist.categories", target = "categoriesDto")
    PlaylistDto toDto(Playlist playlist);

    Playlist fromDto(PlaylistDto playlistDto);
}
