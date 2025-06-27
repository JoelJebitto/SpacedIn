package com.joeljebitto.SpacedIn.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "card_progress", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "card_id" }))
public class CardProgress {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "card_id", nullable = false)
  private Card card;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false, precision = 5, scale = 2)
  private BigDecimal progress;

  // Constructors
  public CardProgress() {
  }

  public CardProgress(Card card, User user, BigDecimal progress) {
    this.card = card;
    this.user = user;
    this.progress = progress;
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

  public BigDecimal getProgress() {
    return progress;
  }

  public void setProgress(BigDecimal progress) {
    this.progress = progress;
  }
}
