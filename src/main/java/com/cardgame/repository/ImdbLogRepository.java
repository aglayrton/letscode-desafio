package com.cardgame.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cardgame.entities.ImdbLog;
import com.cardgame.enu.Gender;

@Repository
public interface ImdbLogRepository extends JpaRepository<ImdbLog,Long> {
    Optional<ImdbLog> findTopByGenderOrderByIdDesc(Gender gender);
}
