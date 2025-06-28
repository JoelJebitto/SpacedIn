package com.joeljebitto.SpacedIn.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joeljebitto.SpacedIn.Entity.Deck;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
  // Add any Deck-specific query methods here, e.g.:
  List<Deck> findAllByUserId(Long userId);

  Optional<Deck> findByIdAndUserId(Long id, Long userId);
}
