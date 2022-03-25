package com.practice.test1.web.dto.channel;

import com.practice.test1.web.dto.channelplaylist.ChannelPlaylistGetDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Channel response")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChannelResponseDto {

    @Schema(description = "Channel id")
    private Long id;

    @Schema(description = "Channel name")
    private String name;

    @Schema(description = "Channel playlists")
    private List<ChannelPlaylistGetDto> playlists;
}
