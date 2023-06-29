package com.waxy.database.repository;

import com.waxy.database.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long> {
}
