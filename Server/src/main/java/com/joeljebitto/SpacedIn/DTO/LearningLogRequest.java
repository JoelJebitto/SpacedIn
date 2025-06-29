package com.joeljebitto.SpacedIn.DTO;

import jakarta.validation.constraints.NotNull;

public class LearningLogRequest {
  @NotNull
  private Boolean status;

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }
}
