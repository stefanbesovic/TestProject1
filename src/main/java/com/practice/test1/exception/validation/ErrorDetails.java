package com.practice.test1.exception.validation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Map;

@Schema(description = "Validation error details")
@Data
@Builder
public class ErrorDetails {

    @Schema(description = "Timestamp of error")
    Timestamp timestamp;

    @Schema(description = "Error path")
    String path;

    @Schema(description = "Error details")
    Map<String, String> validationErorr;
}
