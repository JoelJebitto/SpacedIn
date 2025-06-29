package com.joeljebitto.SpacedIn.repository;

import com.joeljebitto.SpacedIn.entity.Deck;
import com.joeljebitto.SpacedIn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeckRepository extends JpaRepository<Deck, Long> {
    List<Deck> findByOwner(User owner);
}
