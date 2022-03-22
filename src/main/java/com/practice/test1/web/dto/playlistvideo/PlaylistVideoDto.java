package com.practice.test1.web.dto.playlistvideo;

import com.practice.test1.web.dto.playlist.PlaylistDto;
import com.practice.test1.web.dto.video.VideoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaylistVideoDto {
    private PlaylistDto playlistDto;
    private VideoDto videoDto;
    private int position;
}
