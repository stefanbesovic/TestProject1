package com.practice.test1.web.dto.playlist;

import com.practice.test1.web.dto.playlistvideo.PlaylistVideoDto;
import com.practice.test1.web.dto.category.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaylistDto {
    private String name;
    private Set<CategoryDto> categoriesDto;
    private List<PlaylistVideoDto> videosDto;
}
