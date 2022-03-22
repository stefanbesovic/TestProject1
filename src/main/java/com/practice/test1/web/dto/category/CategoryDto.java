package com.practice.test1.web.dto.category;

import com.practice.test1.web.dto.playlist.PlaylistDto;
import com.practice.test1.web.dto.video.VideoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private String name;
}
