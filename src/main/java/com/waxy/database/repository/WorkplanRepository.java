package com.waxy.database.repository;

import com.waxy.database.entity.Workplan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkplanRepository extends JpaRepository<Workplan, Long> {
}
