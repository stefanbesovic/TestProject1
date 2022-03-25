package com.practice.test1.web.dto.channelplaylist;

import com.practice.test1.web.dto.playlist.PlaylistGetDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "ChannelPlaylist response")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChannelPlaylistGetDto {

    @Schema(description = "Playlist")
    private PlaylistGetDto playlist;

    @Schema(description = "Playlist position")
    private int position;
}
