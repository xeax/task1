package com.example.task.vote;

import java.util.List;

public interface VoteService {
    VoteDto getMyVote(long quoteId);
    VoteDto vote(long quoteId, VoteDto voteDto);
    List<VoteDayStatDto> getVoteStatistics(long quoteId, long lastDays);
}
