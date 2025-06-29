package com.joeljebitto.SpacedIn.dto;

public class FlashcardRequest {
    private Long deckId;
    private String question;
    private String answer;

    public Long getDeckId() { return deckId; }
    public void setDeckId(Long deckId) { this.deckId = deckId; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
}
