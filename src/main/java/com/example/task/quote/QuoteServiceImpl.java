package com.example.task.quote;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import javax.transaction.Transactional;

import com.example.task.quote.exceptions.QuoteNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;

    private final QuoteMapper quoteMapper;

    @Override
    public QuoteDto get(long id) {
        Optional<Quote> quote = quoteRepository.findById(id);
        return quote.map(quoteMapper::map)
                .orElseThrow(() -> new QuoteNotFoundException(id));
    }

    @Override
    @Transactional
    public QuoteDto create(QuoteDto quoteDto) {
        Quote quote = quoteMapper.map(quoteDto);
        Quote savedQuote = quoteRepository.saveAndFlush(quote);
        return quoteMapper.map(savedQuote);
    }

    @Override
    @Transactional
    public void update(long id, QuoteDto quoteDto) {
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException(id));
        Quote updatedQuote = quoteMapper.map(quoteDto, quote);
        quoteRepository.saveAndFlush(updatedQuote);
    }

    @Override
    @Transactional
    public void delete(long id) {
        if (!quoteRepository.existsById(id)) {
            throw new QuoteNotFoundException(id);
        }
        quoteRepository.deleteById(id);
    }
}
