package com.practice.test1.exception.validation;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Map;

@Data
@Builder
public class ErrorDetails {
    Timestamp timestamp;
    String path;
    Map<String, String> validationErorr;
}
