package com.joeljebitto.SpacedIn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import com.joeljebitto.SpacedIn.entity.CardProgress;

import jakarta.persistence.*;

@Entity
public class Flashcard {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Lob
  @Column(columnDefinition = "TEXT")
  private String question;

  @Lob
  @Column(columnDefinition = "TEXT")
  private String answer;
  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "deck_id")
  private Deck deck;

  @JsonIgnore
  @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CardProgress> progressRecords = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public Deck getDeck() {
    return deck;
  }

  public void setDeck(Deck deck) {
    this.deck = deck;
  }

  public List<CardProgress> getProgressRecords() {
    return progressRecords;
  }

  public void setProgressRecords(List<CardProgress> progressRecords) {
    this.progressRecords = progressRecords;
  }
}
