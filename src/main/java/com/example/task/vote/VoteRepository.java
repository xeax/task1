package com.example.task.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.task.quote.Quote;
import com.example.task.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
	Optional<Vote> findByQuoteAndUser(Quote quote, User user);
	List<Vote> findAllByQuoteAndModifiedAtAfter(Quote quote, LocalDateTime after);
}
