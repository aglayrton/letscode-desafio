package com.cardgame.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cardgame.entities.Quiz;
import com.cardgame.entities.User;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>{
	Optional<Quiz> findTopByUserAndGameOverOrderByIdDesc(User user, boolean gameOver);
	
	@Query(nativeQuery = true, value = "SELECT * FROM tb_note ORDER BY pont")
	List<Quiz> findRanking();
}
