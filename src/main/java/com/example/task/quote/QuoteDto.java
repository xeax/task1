package com.example.task.quote;

import javax.validation.constraints.NotBlank;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class QuoteDto {
    private Long id;

    @NotBlank
    private String content;

    private String createdBy;

    private LocalDateTime createdAt;

    private Long rating;
}
