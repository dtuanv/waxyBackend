package com.waxy.database.repository;

import com.waxy.database.entity.NotWorkable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface NotWorkableRepository extends JpaRepository<NotWorkable,Long> {
    @Query(value="SELECT * FROM not_workable WHERE business_id = ?1", nativeQuery = true)
    List<NotWorkable> findByBusinessId(long businessId);

    @Query(value="SELECT * FROM not_workable WHERE business_id =?1 AND to_date >= ?2 AND to_date <= ?3", nativeQuery = true)
    Set<NotWorkable> findNotWorkableMonthsBecauseOfSchool(long businessId,String start, String end);
}
