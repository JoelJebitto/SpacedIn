package com.joeljebitto.SpacedIn.repository;

import com.joeljebitto.SpacedIn.entity.Deck;
import com.joeljebitto.SpacedIn.entity.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    List<Flashcard> findByDeck(Deck deck);
}
