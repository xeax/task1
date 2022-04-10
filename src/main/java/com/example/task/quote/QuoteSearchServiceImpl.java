package com.example.task.quote;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import com.example.task.quote.exceptions.QuotesNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteSearchServiceImpl implements QuoteSearchService {

    private final QuoteRepository quoteRepository;

    private final QuoteMapper quoteMapper;

    @Override
    public QuoteDto getRandom() {
        int qty = (int)quoteRepository.count();
        int idx = (int)(Math.random() * qty);
        Page<Quote> quotePage = quoteRepository.findAll(PageRequest.of(idx, 1));
        if (!quotePage.hasContent()){
            throw new QuotesNotFoundException();
        }
        Quote randomQuote = quotePage.getContent().get(0);
        return quoteMapper.map(randomQuote);
    }

    @Override
    public List<QuoteDto> getLatest10(){
        return quoteRepository.findFirst10ByOrderByIdDesc().stream()
                .map(quoteMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuoteDto> getTop10(){
        return quoteRepository.findFirst10ByOrderByRatingDescIdDesc().stream()
                .map(quoteMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuoteDto> getFlop10(){
        return quoteRepository.findFirst10ByOrderByRatingAscIdDesc().stream()
                .map(quoteMapper::map)
                .collect(Collectors.toList());
    }
}
