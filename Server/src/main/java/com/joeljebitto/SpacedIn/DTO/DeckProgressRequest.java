package com.joeljebitto.SpacedIn.DTO;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DeckProgressRequest {
  @NotNull
  private BigDecimal progress;

  public BigDecimal getProgress() {
    return progress;
  }

  public void setProgress(BigDecimal progress) {
    this.progress = progress;
  }
}
