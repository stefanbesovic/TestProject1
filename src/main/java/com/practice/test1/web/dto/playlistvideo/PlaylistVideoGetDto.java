package com.practice.test1.web.dto.playlistvideo;

import com.practice.test1.web.dto.video.VideoGetDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaylistVideoGetDto {

    private VideoGetDto video;
    private int position;
}
