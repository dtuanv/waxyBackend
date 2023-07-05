package com.waxy.database.repository;

import com.waxy.database.entity.Workplan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface WorkplanRepository extends JpaRepository<Workplan, Long> {

    @Query(value="SELECT * FROM workplan WHERE business_id = ?1", nativeQuery = true)
    Set<Workplan> findWorkplanByBusinessId(int businessId);
}
