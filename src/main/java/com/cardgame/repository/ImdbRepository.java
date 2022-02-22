package com.cardgame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cardgame.entities.Imdb;

@Repository
public interface ImdbRepository extends JpaRepository<Imdb,Long> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM imdb ORDER BY RAND () LIMIT 2")
	List<Imdb> findAllRandom();
	
}
