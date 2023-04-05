package com.waxy.database.repository;


import com.waxy.database.entity.ThemeTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ThemeTopicRepository  extends JpaRepository<ThemeTopic, Long> {
    @Query(value="SELECT * FROM theme_topic WHERE topic_id = ?1", nativeQuery = true)
    Set<ThemeTopic> getAllThemeTopicByTopicId(long id);
}
