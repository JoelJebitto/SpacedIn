package com.joeljebitto.SpacedIn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joeljebitto.SpacedIn.Entity.DeckProgress;

import java.util.List;

@Repository
public interface DeckProgressRepository extends JpaRepository<DeckProgress, Long> {
  // Find all progress entries for a given user
  List<DeckProgress> findByUserId(Long userId);

  // Find all progress entries for a given deck
  List<DeckProgress> findByDeckId(Long deckId);

  // Find the progress entry for a specific user+deck
  DeckProgress findByUserIdAndDeckId(Long userId, Long deckId);
}
