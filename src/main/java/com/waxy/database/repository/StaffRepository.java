package com.waxy.database.repository;

import com.waxy.database.entity.StaffItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StaffRepository extends JpaRepository<StaffItem, Long> {
    @Query(value="SELECT * FROM staff_item WHERE workplan_id = ?1", nativeQuery = true)
    Set<StaffItem> findAllByWorkplan(long workplanId);
}
