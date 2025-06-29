package com.joeljebitto.SpacedIn.dto;

import com.joeljebitto.SpacedIn.entity.Deck;

public class DeckDTO {
  private Long id;
  private String title;

  public DeckDTO(Long id, String title) {
    this.id = id;
    this.title = title;
  }

  public DeckDTO(Deck deck) {
    this.id = deck.getId();
    this.title = deck.getTitle();
  }

  // Getters
  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }
}
