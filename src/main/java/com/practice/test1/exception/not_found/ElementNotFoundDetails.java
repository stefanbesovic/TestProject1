package com.practice.test1.exception.not_found;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ElementNotFoundDetails {
    Timestamp timestamp;
    String path;
    String message;
}
