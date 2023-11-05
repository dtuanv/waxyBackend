package com.waxy.database.repository;

import com.waxy.database.entity.LAnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LAnswerOptionRepository extends JpaRepository<LAnswerOption, Long> {
}
