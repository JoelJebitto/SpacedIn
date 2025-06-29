package com.joeljebitto.SpacedIn.DTO;

import java.math.BigDecimal;

public class DeckProgressResponse {
  private Long id;
  private BigDecimal progress;
  private Long deckId;
  private Long userId;

  public DeckProgressResponse(Long id, BigDecimal progress, Long deckId, Long userId) {
    this.id = id;
    this.progress = progress;
    this.deckId = deckId;
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public BigDecimal getProgress() {
    return progress;
  }

  public Long getDeckId() {
    return deckId;
  }

  public Long getUserId() {
    return userId;
  }
}
