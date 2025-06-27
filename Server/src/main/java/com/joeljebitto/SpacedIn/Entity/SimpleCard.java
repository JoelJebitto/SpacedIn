package com.joeljebitto.SpacedIn.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

@Entity
public class SimpleCard extends Card {

  @Lob
  private String front;

  @Lob
  private String back;

  @Override
  public String toString() {
    return "SimpleCard [front=" + front + ", back=" + back + "]";
  }

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
