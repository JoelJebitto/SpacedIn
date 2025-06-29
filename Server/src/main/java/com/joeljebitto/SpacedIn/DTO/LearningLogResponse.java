package com.joeljebitto.SpacedIn.DTO;

import java.time.LocalDateTime;

public class LearningLogResponse {
  private Long id;
  private Boolean status;
  private LocalDateTime eventTime;
  private Long cardId;
  private Long userId;

  public LearningLogResponse(Long id, Boolean status, LocalDateTime eventTime, Long cardId, Long userId) {
    this.id = id;
    this.status = status;
    this.eventTime = eventTime;
    this.cardId = cardId;
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public Boolean getStatus() {
    return status;
  }

  public LocalDateTime getEventTime() {
    return eventTime;
  }

  public Long getCardId() {
    return cardId;
  }

  public Long getUserId() {
    return userId;
  }
}
