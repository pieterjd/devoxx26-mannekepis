package com.pieterjd.devoxx26.clevercloud;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PissedOffSelectionRepository extends JpaRepository<PissedOffSelection, Long> {

    @Query("select count(s) from PissedOffSelection s where s.mood = :mood")
    long countByMood(@Param("mood") String mood);

    Optional<PissedOffSelection> findTopByOrderByIdDesc();
}
