package org.springframework.samples.shield.model;

import lombok.Data;

@Data
public class InputMetadata {
    private SourceType sourceType;
    private String inputId;
}