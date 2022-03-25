package com.practice.test1.web.dto.category;

import com.practice.test1.web.dto.playlist.PlaylistGetDto;
import com.practice.test1.web.dto.video.VideoGetDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Schema(description = "Category request")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    @Schema(description = "category name")
    @NotEmpty
    @Size(min = 3, max = 15, message = "Category name should be between 3 and 15 characters")
    private String name;

    @Schema(description = "playlists with category")
    private Set<PlaylistGetDto> playlists;

    @Schema(description = "videos with category")
    private Set<VideoGetDto> videos;
}
