package com.joeljebitto.SpacedIn.DTO;

public class SimpleCardResponse {
  private Long id;
  private String front;
  private String back;
  private Long deckId;

  public SimpleCardResponse(Long id, String front, String back, Long deckId) {
    this.id = id;
    this.front = front;
    this.back = back;
    this.deckId = deckId;
  }

  public Long getId() {
    return id;
  }

  public String getFront() {
    return front;
  }

  public String getBack() {
    return back;
  }

  public Long getDeckId() {
    return deckId;
  }
}
