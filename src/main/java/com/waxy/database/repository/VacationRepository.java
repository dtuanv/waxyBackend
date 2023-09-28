package com.waxy.database.repository;

import com.waxy.database.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.LinkedHashSet;
import java.util.Set;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long> {
    @Query(value="SELECT * FROM vacation WHERE business_id= ?1 AND is_confirmed = true", nativeQuery = true)
    Set<Vacation> findAllByBusinessId(int businessId);

    @Query(value="SELECT count(*) FROM vacation WHERE is_confirmed = false AND business_id = ?1" , nativeQuery = true)
    int countVacationIsNotConfirm(int businessId);

    @Query(value="SELECT * FROM vacation WHERE is_confirmed = false AND is_rejected = false AND business_id = ?1" , nativeQuery = true)
    LinkedHashSet<Vacation> findVacationIsNotConfirm(int businessId);

    @Query(value="SELECT * FROM vacation WHERE is_confirmed = false AND is_rejected = false AND user_info_id = ?1" , nativeQuery = true)
    LinkedHashSet<Vacation> findVacationIsNotConfirmByUserInfoId(int userInfoId);


    @Transactional
    @Modifying
    @Query(value="UPDATE vacation SET is_rejected = true WHERE id = ?1", nativeQuery = true)
    void updateVacationColumnIsRejected(long vacationId);


    @Transactional
    @Modifying
    @Query(value="UPDATE vacation SET is_Confirmed = true WHERE id = ?1", nativeQuery = true)
    void updateVacationColumnIsConfirmed(long vacationId);

    @Query(value="SELECT * FROM vacation WHERE business_id =?1 AND to_date >= ?2 AND is_confirmed = true", nativeQuery = true)
    Set<Vacation> findNotWorkableBecauseOfVacation(long businessId, String start);
}
