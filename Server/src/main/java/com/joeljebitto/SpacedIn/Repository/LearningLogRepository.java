package com.joeljebitto.SpacedIn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joeljebitto.SpacedIn.Entity.LearningLog;

import java.util.List;

@Repository
public interface LearningLogRepository extends JpaRepository<LearningLog, Long> {
  // Find all logs for a given user
  List<LearningLog> findByUserId(Long userId);

  // Find all logs for a given card
  List<LearningLog> findByCardId(Long cardId);

  // Find all logs for a given user & card
  List<LearningLog> findByUserIdAndCardId(Long userId, Long cardId);
}
