package com.joeljebitto.SpacedIn.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joeljebitto.SpacedIn.Entity.Deck;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
  // Add any Deck-specific query methods here, e.g.:
  List<Deck> findByUserId(Long userId);
}
