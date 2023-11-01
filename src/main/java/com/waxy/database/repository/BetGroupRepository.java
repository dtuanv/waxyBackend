package com.waxy.database.repository;

import com.waxy.database.entity.BetGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetGroupRepository extends JpaRepository<BetGroup,Long> {
}
