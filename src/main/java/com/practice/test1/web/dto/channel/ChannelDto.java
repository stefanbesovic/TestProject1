package com.practice.test1.web.dto.channel;

import com.practice.test1.web.dto.channelplaylist.ChannelPlaylistGetDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChannelDto {

    private long id;

    @NotEmpty
    @Size(min = 3, max = 20, message = "Name of channel should be between 3 and 20 characters.")
    private String name;

    private List<ChannelPlaylistGetDto> playlists;
}
