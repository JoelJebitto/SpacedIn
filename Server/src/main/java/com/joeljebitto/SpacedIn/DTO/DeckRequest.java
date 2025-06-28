package com.joeljebitto.SpacedIn.DTO;

import jakarta.validation.constraints.NotBlank;

public class DeckRequest {
  @NotBlank
  private String title;

  private String description;

  // getters & setters
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
