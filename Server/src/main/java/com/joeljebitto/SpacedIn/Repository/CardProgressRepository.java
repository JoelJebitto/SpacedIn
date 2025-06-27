package com.joeljebitto.SpacedIn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joeljebitto.SpacedIn.Entity.CardProgress;

import java.util.List;

@Repository
public interface CardProgressRepository extends JpaRepository<CardProgress, Long> {
  // Find all progress entries for a given user
  List<CardProgress> findByUserId(Long userId);

  // Find all progress entries for a given card
  List<CardProgress> findByCardId(Long cardId);

  // Find the progress entry for a specific user+card
  CardProgress findByUserIdAndCardId(Long userId, Long cardId);
}
