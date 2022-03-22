package com.practice.test1.web.dto.playlistvideo;

import com.practice.test1.entities.PlaylistVideo;
import com.practice.test1.web.dto.playlist.PlaylistMapper;
import com.practice.test1.web.dto.video.VideoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {VideoMapper.class, PlaylistMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlaylistVideoMapper {
    PlaylistVideoMapper INSTANCE = Mappers.getMapper(PlaylistVideoMapper.class);

    //@Mapping(source = "playlistVideo.video", target = "videoDto")
    //@Mapping(source = "playlistVideo.playlist", target = "playlistDto")
    PlaylistVideoDto toDto(PlaylistVideo playlistVideo);

    PlaylistVideo fromDto(PlaylistVideoDto playlistVideoDto);
}
