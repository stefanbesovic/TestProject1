package com.practice.test1.web.dto.channel;

import com.practice.test1.entities.Channel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChannelMapper {
    ChannelMapper INSTANCE = Mappers.getMapper(ChannelMapper.class);

    //@Mapping(source = "channel.playlists", target = "playlists")
    ChannelDto toDto(Channel channel);
    Channel fromDto(ChannelDto channelDto);
}
