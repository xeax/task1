package com.example.task.quote;

import java.util.List;

public interface QuoteSearchService {
    QuoteDto getRandom();
    List<QuoteDto> getLatest10();
    List<QuoteDto> getTop10();
    List<QuoteDto> getFlop10();
}
