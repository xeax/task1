package com.example.task.quote;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The quote mapper.
 */
@Mapper
public interface QuoteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Quote map(QuoteDto quoteDto);

    @Mapping(target = "id", source = "quote.id")
    @Mapping(target = "content", source = "quoteDto.content")
    @Mapping(target = "rating", source = "quote.rating")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Quote map(QuoteDto quoteDto, Quote quote);

    @Mapping(target = "createdBy", source = "createdBy.login")
    QuoteDto map(Quote quote);
}
