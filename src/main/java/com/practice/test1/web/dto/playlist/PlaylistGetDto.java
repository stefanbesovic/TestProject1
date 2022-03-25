package com.practice.test1.web.dto.playlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaylistGetDto {

    @NotEmpty
    @Size(min = 4, max = 20, message = "Name of playlist should be between 3 and 20 characters.")
    private String name;
}
