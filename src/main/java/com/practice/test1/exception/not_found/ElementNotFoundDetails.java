package com.practice.test1.exception.not_found;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Schema(description = "Not found error details")
@Data
@Builder
public class ElementNotFoundDetails {

    @Schema(description = "Timestamp of error")
    Timestamp timestamp;

    @Schema(description = "Error path")
    String path;

    @Schema(description = "Error details")
    String message;
}
