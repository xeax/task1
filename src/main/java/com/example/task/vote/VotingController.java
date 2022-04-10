package com.example.task.vote;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("voting")
@RequiredArgsConstructor
@Api(tags = "Voting controller")
public class VotingController {

    private final VoteService voteService;

    @GetMapping("/{id}")
    @ApiOperation("Get my vote for quote by id")
    public ResponseEntity<VoteDto> getMyVote(@PathVariable("id") long id) {
        return ResponseEntity.ok(voteService.getMyVote(id));
    }

    @PostMapping("/{id}")
    @ApiOperation("Vote for quote by id")
    public ResponseEntity<VoteDto> vote(@PathVariable("id") long id, @Valid @RequestBody VoteDto voteDto) {
        return ResponseEntity.ok(voteService.vote(id, voteDto));
    }

    @GetMapping("/{id}/statistics")
    @ApiOperation("Get last 20 days voting statistics for quote by id")
    public ResponseEntity<List<VoteDayStatDto>> getVoteStatistics(@PathVariable("id") long id) {
        return ResponseEntity.ok(voteService.getVoteStatistics(id, 20));
    }

}