package com.practice.test1.web.dto.channelplaylist;

import com.practice.test1.web.dto.playlist.PlaylistGetDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChannelPlaylistGetDto {
    private PlaylistGetDto playlist;
    private int position;
}
