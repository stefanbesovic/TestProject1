package com.practice.test1.web.dto.channelplaylist;

import com.practice.test1.entities.ChannelPlaylist;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChannelPlaylistGetMapper {
    ChannelPlaylistGetMapper INSTANCE = Mappers.getMapper(ChannelPlaylistGetMapper.class);

    ChannelPlaylistGetDto toDto(ChannelPlaylist channelPlaylist);
    ChannelPlaylist fromDto(ChannelPlaylistGetDto channelPlaylistGetDto);
}
