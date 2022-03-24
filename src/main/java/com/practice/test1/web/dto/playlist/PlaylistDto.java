package com.practice.test1.web.dto.playlist;

import com.practice.test1.web.dto.playlistvideo.PlaylistVideoGetDto;
import com.practice.test1.web.dto.category.CategoryGetDto;
import com.practice.test1.web.dto.user.UserGetDto;
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
    private long id;
    private String name;
    private Set<CategoryGetDto> categories;
    private List<PlaylistVideoGetDto> videos;
    private UserGetDto user;
}
