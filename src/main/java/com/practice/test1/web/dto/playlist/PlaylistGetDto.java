package com.practice.test1.web.dto.playlist;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Schema(description = "Playlist request")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaylistGetDto {

    @Schema(description = "Playlist name")
    @NotEmpty
    @Size(min = 4, max = 20, message = "Name of playlist should be between 3 and 20 characters.")
    private String name;
}
