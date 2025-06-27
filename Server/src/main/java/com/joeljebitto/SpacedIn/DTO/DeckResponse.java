package com.joeljebitto.SpacedIn.DTO;

import java.time.LocalDateTime;

public class DeckResponse {
  private Long id;
  private String title;
  private String description;
  private LocalDateTime createdOn;
  private Long userId;

  public DeckResponse(Long id, String title, String description, LocalDateTime createdOn, Long userId) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.createdOn = createdOn;
    this.userId = userId;
  }

  // getters only
  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public LocalDateTime getCreatedOn() {
    return createdOn;
  }

  public Long getUserId() {
    return userId;
  }
}
