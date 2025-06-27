package com.joeljebitto.SpacedIn.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joeljebitto.SpacedIn.Entity.SimpleCard;

@Repository
public interface SimpleCardRepository extends JpaRepository<SimpleCard, Long> {
  // Add SimpleCard-specific queries if required, e.g.:
  List<SimpleCard> findByDeckId(Long deckId);
}
