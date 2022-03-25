package com.practice.test1.web.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Category Request")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryGetDto {

    @Schema(description = "category id")
    private Long id;

    @Schema(description = "category name")
    private String name;
}
