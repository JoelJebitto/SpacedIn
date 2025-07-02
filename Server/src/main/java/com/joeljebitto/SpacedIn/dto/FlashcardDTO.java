package com.joeljebitto.SpacedIn.dto;

import com.joeljebitto.SpacedIn.entity.Flashcard;

public class FlashcardDTO {
  private Long id;
  private String question;
  private String answer;

  public FlashcardDTO(Long id, String question, String answer) {
    this.id = id;
    this.question = question;
    this.answer = answer;
  }

  // (Optionally keep the constructor that accepts Flashcard)
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

  public void setId(Long id) {
    this.id = id;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }
}
