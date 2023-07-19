package com.waxy.database.repository;

import com.waxy.database.entity.WishMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface WishMessageRepository extends JpaRepository<WishMessage, Long> {

    @Query(value="SELECT * FROM wish_message WHERE b_user_id = ?1", nativeQuery = true)
    Set<WishMessage> findWishMessageByBUserId(long bUserId);

    @Query(value="SELECT * FROM wish_message WHERE from_user_id = ?1 AND birth_user_id = ?2 AND today LIKE ?3", nativeQuery = true)
    Set<WishMessage> checkWishMessageFromUser(long fromUserId, long birthUserId, String today);
}
