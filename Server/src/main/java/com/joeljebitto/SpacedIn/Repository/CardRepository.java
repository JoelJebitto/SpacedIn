package com.joeljebitto.SpacedIn.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joeljebitto.SpacedIn.Entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
  // Polymorphic: will return any subtype of Card (SimpleCard, ImageCard, etc.)
  // You can also declare subtype queries here if needed:
  List<Card> findByDeckId(Long deckId);
}
