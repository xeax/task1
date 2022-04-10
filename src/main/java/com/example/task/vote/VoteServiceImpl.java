package com.example.task.vote;

import lombok.RequiredArgsConstructor;

import com.example.task.quote.Quote;
import com.example.task.quote.QuoteRepository;
import com.example.task.quote.exceptions.QuoteNotFoundException;
import com.example.task.tools.AuthenticatedUserTool;
import com.example.task.user.User;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.List;
import java.util.SortedMap;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final QuoteRepository quoteRepository;

    private final VoteRepository voteRepository;

    private final VoteMapper voteMapper;

    private final AuthenticatedUserTool authenticatedUserTool;

    @Override
    public VoteDto getMyVote(long quoteId) {
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new QuoteNotFoundException(quoteId));
        User user = authenticatedUserTool.getUser();
        return voteRepository.findByQuoteAndUser(quote, user)
                .map(voteMapper::map)
                .orElse(new VoteDto());
    }

    @Override
    @Transactional
    public VoteDto vote(long quoteId, VoteDto voteDto) {
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new QuoteNotFoundException(quoteId));
        User user = authenticatedUserTool.getUser();

        Optional<Vote> existingVote = voteRepository.findByQuoteAndUser(quote, user);
        long votingValue = (long) voteDto.getStatus().getValue();
        Vote updatedVote = voteMapper.map(voteDto);
        if (existingVote.isPresent()){
            updatedVote = voteMapper.map(voteDto, existingVote.get());
            votingValue -= existingVote.get().getStatus().getValue();
        }

        updatedVote.setQuote(quote);
        updatedVote.setUser(user);

        quoteRepository.registerVote(quoteId, votingValue);
        Vote savedVote = voteRepository.saveAndFlush(updatedVote);
        return voteMapper.map(savedVote);
    }

    @Override
    public List<VoteDayStatDto> getVoteStatistics(long quoteId, long lastDays){
        // TODO: Refactor for better perfomance
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new QuoteNotFoundException(quoteId));
        SortedMap<LocalDate, Long> result = new TreeMap<>();
        SortedMap<LocalDate, Long> result2 = new TreeMap<>();
        LocalDateTime after = LocalDate.now().minusDays(lastDays).atStartOfDay();
        List<Vote> votes = voteRepository.findAllByQuoteAndModifiedAtAfter(quote, after);
        for(Vote v: votes){
            LocalDate date = v.getModifiedAt().toLocalDate();
            if (result.get(date) == null){
                result.put(date, 0L);
                result2.put(date, 0L);
            }
            result.put(date, result.get(date) + v.getValue());
            result2.put(date, result2.get(date) + 1);
        }
        return result.keySet().stream()
                .map(date -> new VoteDayStatDto(date, result.get(date), result2.get(date)))
                .collect(Collectors.toList());
    }
}
