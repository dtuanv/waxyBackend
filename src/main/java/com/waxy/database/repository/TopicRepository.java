package com.waxy.database.repository;

import com.waxy.database.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query(value="SELECT * FROM topic AS t WHERE t.user_info_id = ?1", nativeQuery = true)
    Set<Topic> findAllTopicsByUserInfoId(long userInfoId);
}
