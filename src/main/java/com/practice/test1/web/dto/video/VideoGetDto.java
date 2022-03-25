package com.practice.test1.web.dto.video;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Video response")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoGetDto {

    @Schema(description = "Video id")
    private Long id;

    @Schema(description = "Video name")
    private String name;
}
