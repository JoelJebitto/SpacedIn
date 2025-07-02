package com.joeljebitto.SpacedIn.repository;

import com.joeljebitto.SpacedIn.dto.FlashcardDTO;
import com.joeljebitto.SpacedIn.entity.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
  List<Flashcard> findByDeckId(Long deckId);

  @Query("SELECT new com.joeljebitto.SpacedIn.dto.FlashcardDTO(f.id, f.question, f.answer) FROM Flashcard f WHERE f.deck.id = :deckId")
  List<FlashcardDTO> findFlashcardDTOsByDeckId(@Param("deckId") Long deckId);
}
