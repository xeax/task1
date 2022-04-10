package com.example.task.vote;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VoteDayStatDto {
    private LocalDate date;
    private Long ratingChange;
    private Long votes;
}
