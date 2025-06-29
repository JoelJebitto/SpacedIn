package com.joeljebitto.SpacedIn.DTO;

import java.math.BigDecimal;

public class CardProgressResponse {
  private Long id;
  private BigDecimal progress;
  private Long cardId;
  private Long userId;

  public CardProgressResponse(Long id, BigDecimal progress, Long cardId, Long userId) {
    this.id = id;
    this.progress = progress;
    this.cardId = cardId;
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public BigDecimal getProgress() {
    return progress;
  }

  public Long getCardId() {
    return cardId;
  }

  public Long getUserId() {
    return userId;
  }
}
