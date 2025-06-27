package com.joeljebitto.SpacedIn.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "learning_log")
public class LearningLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "card_id", nullable = false)
  private Card card;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false)
  private Boolean status;

  @CreationTimestamp
  @Column(name = "event_time", nullable = false, updatable = false)
  private LocalDateTime eventTime;

  // Constructors
  public LearningLog() {
  }

  public LearningLog(Card card, User user, Boolean status) {
    this.card = card;
    this.user = user;
    this.status = status;
  }

  // Getters & Setters
  public Long getId() {
    return id;
  }

  public Card getCard() {
    return card;
  }

  public void setCard(Card card) {
    this.card = card;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public LocalDateTime getEventTime() {
    return eventTime;
  }
}
