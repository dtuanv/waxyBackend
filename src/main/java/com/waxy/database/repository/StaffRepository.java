package com.waxy.database.repository;

import com.waxy.database.entity.StaffItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffItem, Long> {
}
