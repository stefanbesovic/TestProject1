package com.practice.test1.web.dto.video;

import com.practice.test1.web.dto.category.CategoryGetDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Schema(description = "Video request")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoDto {

    @Schema(description = "Video name")
    @NotEmpty
    @Size(min = 3, max = 20, message = "Name of video should be between 3 and 20 characters.")
    private String name;

    @Schema(description = "Video vategories")
    private Set<CategoryGetDto> categories;
}
