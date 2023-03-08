package com.waxy.database.repository;


import com.waxy.database.entity.ThemeTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeTopicRepository  extends JpaRepository<ThemeTopic, Long> {
}
