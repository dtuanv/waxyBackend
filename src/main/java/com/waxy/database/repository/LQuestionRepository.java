package com.waxy.database.repository;

import com.waxy.database.entity.LQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LQuestionRepository extends JpaRepository<LQuestion, Long> {
}
