package com.practice.test1.web.dto.playlist;

import com.practice.test1.web.dto.playlistvideo.PlaylistVideoGetDto;
import com.practice.test1.web.dto.category.CategoryGetDto;
import com.practice.test1.web.dto.user.UserGetDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Schema(description = "Playlist response")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaylistDto {

    @Schema(description = "Playlist id")
    private Long id;

    @Schema(description = "Playlist name")
    private String name;

    @Schema(description = "Playlist categories")
    private Set<CategoryGetDto> categories;

    @Schema(description = "Playlist videos")
    private List<PlaylistVideoGetDto> videos;

    @Schema(description = "Playlist user")
    private UserGetDto user;
}
