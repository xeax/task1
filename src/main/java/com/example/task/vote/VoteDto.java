package com.example.task.vote;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class VoteDto {
    private Long id;
    private Long quoteId;
    private Long userId;

    @NotNull
    private VoteStatus status;

    private LocalDateTime modifiedAt;
}
