package com.waxy.database.repository;

import com.waxy.database.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {

    @Query(value = "SELECT COUNT(*) FROM user_info WHERE business_id = ?1", nativeQuery = true)
    Integer countUserOfBusiness(long businessId);
    @Query("From UserDTO WHERE username=?1")
    Optional<UserDTO> findByUsername(String username);



}
