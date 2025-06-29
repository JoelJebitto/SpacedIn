package com.joeljebitto.SpacedIn.DTO;

import jakarta.validation.constraints.NotBlank;

public class SimpleCardRequest {
  @NotBlank
  private String front;
  @NotBlank
  private String back;

  public String getFront() {
    return front;
  }

  public void setFront(String front) {
    this.front = front;
  }

  public String getBack() {
    return back;
  }

  public void setBack(String back) {
    this.back = back;
  }
}
