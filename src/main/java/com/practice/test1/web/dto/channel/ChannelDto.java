package com.practice.test1.web.dto.channel;

import com.practice.test1.web.dto.channelplaylist.ChannelPlaylistGetDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChannelDto {
    private long id;
    private String name;
    private List<ChannelPlaylistGetDto> playlists;
}
