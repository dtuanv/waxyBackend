package com.waxy.database.repository;

import com.waxy.database.entity.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SentenceRepository extends JpaRepository<Sentence, Long> {

    @Query(value=" SELECT * FROM sentence WHERE topic_id = ?1", nativeQuery = true)
    Set<Sentence> getSentenceByTopicId(long topicId);

    @Query(value="SELECT * FROM sentence \n" +
            "WHERE english ILIKE %?1% OR vietnamese ILIKE %?1% OR conjunction ILIKE %?1% LIMIT 15", nativeQuery = true)
    List<Sentence> findSentenceByKeyword(String keyword);

    @Query(value = "SELECT * FROM sentence AS se\n" +
            "JOIN topic AS tp ON se.topic_id = tp.id \n" +
            "WHERE tp.user_info_id = ?1 AND se.update_at >= now() - interval '1 week' " +
            "ORDER BY se.id DESC", nativeQuery = true)
    List<Sentence> findSentenceInWeekByUserInfoId(long userId);
}
