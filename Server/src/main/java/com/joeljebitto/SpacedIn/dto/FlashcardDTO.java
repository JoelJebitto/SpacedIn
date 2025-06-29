package com.joeljebitto.SpacedIn.dto;

import com.joeljebitto.SpacedIn.entity.Flashcard;

public class FlashcardDTO {
  private Long id;
  private String question;
  private String answer;

  public FlashcardDTO(Flashcard flashcard) {
    this.id = flashcard.getId();
    this.question = flashcard.getQuestion();
    this.answer = flashcard.getAnswer();
  }

  // Getters
  public Long getId() {
    return id;
  }

  public String getQuestion() {
    return question;
  }

  public String getAnswer() {
    return answer;
  }
}
