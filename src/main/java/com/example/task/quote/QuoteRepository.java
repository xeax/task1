package com.example.task.quote;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
	List<Quote> findFirst10ByOrderByIdDesc();

	List<Quote> findFirst10ByOrderByRatingDescIdDesc();

	List<Quote> findFirst10ByOrderByRatingAscIdDesc();

	@Modifying
	@Query(value = "UPDATE Quote q set q.rating = q.rating + ?2 WHERE q.id = ?1")
	void registerVote(Long quoteId, Long vote);
}
