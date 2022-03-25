package com.practice.test1.web.dto.category;

import com.practice.test1.web.dto.playlist.PlaylistGetDto;
import com.practice.test1.web.dto.video.VideoGetDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    @NotEmpty
    @Size(min = 3, max = 15, message = "Category name should be between 3 and 15 characters")
    private String name;

    private Set<PlaylistGetDto> playlists;
    private Set<VideoGetDto> videos;
}
