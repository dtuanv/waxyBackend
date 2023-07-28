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

    @Query(value="SELECT *\n" +
            "FROM wish_message\n" +
            "WHERE\n" +
            "    EXTRACT(MONTH FROM TO_DATE(today, 'DD-MM-YYYY')) = ?1\n" +
            "    AND EXTRACT(DAY FROM TO_DATE(today, 'DD-MM-YYYY')) = ?2\n" +
            "    AND birth_user_id = ?3 AND message != '';",nativeQuery = true)
    Set<WishMessage> findWishMessageToMe(int month,int day, long userId);
}
