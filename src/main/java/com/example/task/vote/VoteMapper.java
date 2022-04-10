package com.example.task.vote;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * The vote mapper.
 */
@Mapper
public interface VoteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "quote", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "value", source = "status", qualifiedByName = "voteStatusToInt")
    Vote map(VoteDto voteDto);

    @Mapping(target = "id", source = "vote.id")
    @Mapping(target = "quote", source = "vote.quote")
    @Mapping(target = "user", source = "vote.user")
    @Mapping(target = "status", source = "voteDto.status")
    @Mapping(target = "value", source = "voteDto.status", qualifiedByName = "voteStatusToInt")
    @Mapping(target = "modifiedAt", ignore = true)
    Vote map(VoteDto voteDto, Vote vote);

    @Mapping(target = "quoteId", source = "quote.id")
    @Mapping(target = "userId", source = "user.id")
    VoteDto map(Vote vote);

    @Named("voteStatusToInt")
    public static int map(VoteStatus status){
        return status.getValue();
    }
}
