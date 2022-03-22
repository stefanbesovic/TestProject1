package com.practice.test1.web.dto.video;

import com.practice.test1.web.dto.category.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoDto {
    private long id;
    private String name;
    private Set<CategoryDto> categoriesDto;
}
