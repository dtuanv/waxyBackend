package com.waxy.database.repository;

import com.waxy.database.entity.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SentenceRepository extends JpaRepository<Sentence, Long> {

    @Query(value=" SELECT * FROM sentence WHERE topic_id = ?1", nativeQuery = true)
    Set<Sentence> getSentenceByTopicId(long topicId);
}
