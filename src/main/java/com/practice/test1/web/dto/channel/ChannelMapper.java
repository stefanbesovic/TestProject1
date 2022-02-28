package com.practice.test1.web.dto.channel;

import com.practice.test1.entities.Channel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChannelMapper {
    ChannelMapper INSTANCE = Mappers.getMapper(ChannelMapper.class);

    ChannelDto toDto(Channel channel);
    Channel fromDto(ChannelDto channelDto);
}
