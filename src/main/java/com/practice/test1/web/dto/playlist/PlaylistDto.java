package com.practice.test1.web.dto.playlist;

import com.practice.test1.web.dto.category.CategoryDto;
import com.practice.test1.web.dto.playlistvideo.PlaylistVideoGetDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaylistDto {
    private long id;

    @NotEmpty
    @Size(min = 4, max = 20, message = "Name of playlist should be between 3 and 20 characters.")
    private String name;

    private Set<CategoryDto> categories;
    private List<PlaylistVideoGetDto> videos;
}
