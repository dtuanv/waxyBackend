package com.waxy.database.repository;

import com.waxy.database.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long> {
    @Query(value="SELECT * FROM vacation WHERE business_id = ?1", nativeQuery = true)
    Set<Vacation> findAllByBusinessId(int businessId);
}
