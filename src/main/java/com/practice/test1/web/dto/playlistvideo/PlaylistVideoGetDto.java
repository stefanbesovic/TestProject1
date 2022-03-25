package com.practice.test1.web.dto.playlistvideo;

import com.practice.test1.web.dto.video.VideoGetDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "PlaylistVideo response")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaylistVideoGetDto {

    @Schema(description = "Video")
    private VideoGetDto video;

    @Schema(description = "Video position")
    private int position;
}
