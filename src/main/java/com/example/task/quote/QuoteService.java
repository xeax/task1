package com.example.task.quote;

public interface QuoteService {
    QuoteDto get(long id);
    QuoteDto create(QuoteDto quoteDto);
    void update(long id, QuoteDto quoteDto);
    void delete(long id);
}
