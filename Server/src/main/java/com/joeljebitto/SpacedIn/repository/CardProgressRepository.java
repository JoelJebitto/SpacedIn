package com.joeljebitto.SpacedIn.repository;

import com.joeljebitto.SpacedIn.entity.CardProgress;
import com.joeljebitto.SpacedIn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardProgressRepository extends JpaRepository<CardProgress, Long> {
    List<CardProgress> findByUser(User user);
}
