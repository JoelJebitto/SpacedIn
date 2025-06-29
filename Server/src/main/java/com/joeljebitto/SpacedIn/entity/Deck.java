package com.joeljebitto.SpacedIn.entity;

import jakarta.persistence.*;

@Entity
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    private User owner;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
}
